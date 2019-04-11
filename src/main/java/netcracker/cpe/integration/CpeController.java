package netcracker.cpe.integration;

import netcracker.cpe.entity.dao.CpeDao;
import netcracker.cpe.entity.dto.CpeDto;
import netcracker.cpe.entity.dto.PeDto;
import netcracker.cpe.service.CpeService;
import netcracker.cpe.service.SpeedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping ( "/cpe" )
public class CpeController {

	private static final String TOPIC = "cpe_topic";
	@Autowired
	private CpeService service;

	@Autowired
	private KafkaTemplate<String, CpeDao> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplateString;


	@GetMapping ( "/all" )
	public List<CpeDao> getAll ( ) {
		return service.findAll();
	}

	@PostMapping ( "/add" )
	public void addOne (@RequestBody CpeDao cpe) {
		if (cpe.isInternetActive() == false) {
			cpe.setDownlinkSpeed(0);
		}
		service.saveCpe(cpe);
	}

	@PostMapping ( "/delete" )
	public void deleteByIp (@PathVariable String ip) {
		service.deleteCpe(ip);
	}

	@GetMapping ( "/{ip}" )
	public CpeDao getByIp (@PathVariable ( "ip" ) String ip) {
		return service.getCpeByIp(ip);
	}

	@PostMapping ( "/internet" ) //for on/off internet
	public @ResponseBody
	ResponseEntity<HttpStatus> changeInternetStatus (@RequestBody CpeDto cpeDto) {
		CpeDao cpeDao = service.getCpeByIp(cpeDto.getIp());
		if (cpeDto.isInternetActive() == cpeDao.isInternetActive()) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		} else {
			cpeDao.setInternetActive(cpeDto.isInternetActive());
			service.saveCpe(cpeDao);
			return new ResponseEntity(HttpStatus.OK);
		}
	}

	@PostMapping ( "/peData" )//for adoption PE and send CPE to kafka
	public void generateCpeSpeed (@RequestBody List<PeDto> list) {
		for (PeDto peDto : list) {
			List<CpeDao> listCpe = service.findAllByPeIpAddressAndIsInternetActive(peDto.getIp());
			SpeedGenerator generator = new SpeedGenerator();
			listCpe = generator.generate(peDto, listCpe);
			for (CpeDao cpe : listCpe) {
				service.saveCpe(cpe);
				kafkaTemplate.send(TOPIC, cpe);
			}
		}
		String linkToAddCpe = "\"http://localhost:8080/cpe/add\"";
		String linkToDeleteCpe = "\"http://localhost:8080/cpe/delete\"";
		String linkToInternet = "\"http://localhost:8080/cpe/internet\"";
		kafkaTemplateString.send(TOPIC, linkToAddCpe);
		kafkaTemplateString.send(TOPIC, linkToDeleteCpe);
		kafkaTemplateString.send(TOPIC, linkToInternet);
	}

	@PostMapping ( "/sendCpe" )//for send Cpe to Pe
	public void sendCpe ( ) {
		RestTemplate rt = new RestTemplate();
		String uri = "http://localhost:8080/cpe/test5";//URL to PE adoption
		List<CpeDao> list = service.findAll();
		rt.postForEntity(uri, list, List.class);
	}

}
