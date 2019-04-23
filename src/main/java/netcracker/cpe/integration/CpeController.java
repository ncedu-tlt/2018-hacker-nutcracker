package netcracker.cpe.integration;

import com.google.gson.Gson;
import netcracker.cpe.entity.dao.CpeDao;
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
	private KafkaTemplate<String, List<CpeDao>> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplateString;

	@GetMapping ( "/all" )
	public List<CpeDao> getAll ( ) {
		return service.findAll();
	}

	@GetMapping ( "/add/{cpeStr}" )
	public void addOne (@PathVariable ( "cpeStr" ) String cpeStr) {
		Gson gson = new Gson();
		CpeDao cpe = gson.fromJson(cpeStr, CpeDao.class);
		cpe.setType("CPE");
		cpe.setSpeed(1000);
		cpe.setSpeed(0);
		cpe.setCoordinateX(100);
		cpe.setCoordinateY(100);
		service.saveCpe(cpe);
	}

	@GetMapping ( "/delete/{ip}" )
	public void deleteByIp (@PathVariable String ip) {
		service.deleteCpe(ip);
	}

	@GetMapping ( "/internet/{ip}" )
	public @ResponseBody
	ResponseEntity<HttpStatus> changeInternetStatus (@PathVariable ( "ip" ) String ip) {
		CpeDao cpeDao = service.getCpeByIp(ip);
		if (cpeDao.isInternetActive()) {
			cpeDao.setInternetActive(false);
			cpeDao.setSpeed(0);
		} else {
			cpeDao.setInternetActive(true);
		}
		service.saveCpe(cpeDao);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping ( "/peData" )
	public void generateCpeSpeed (@RequestBody List<PeDto> list) {
		List<CpeDao> listCpe;
		SpeedGenerator generator = new SpeedGenerator();
		for (PeDto peDto : list) {
			listCpe = service.findAllByPeIpAddressAndInternetActive(peDto.getIp());
			listCpe = generator.generate(peDto, listCpe);
			for (CpeDao cpe : listCpe) {
				service.saveCpe(cpe);
			}
		}
		listCpe = service.findAll();
		kafkaTemplate.send(TOPIC, listCpe);

		String linkToAddCpe = "http://localhost:8080/cpe/add";
		String linkToDeleteCpe = "http://localhost:8080/cpe/delete";
		String linkToInternet = "http://localhost:8080/cpe/internet";
		String totalString = linkToAddCpe + ',' + linkToDeleteCpe + ',' + linkToInternet;
		kafkaTemplateString.send(TOPIC, totalString);
	}

	public void sendCpe ( ) {
		try {
			RestTemplate rt = new RestTemplate();
			String url = "http://localhost:8081/pe/cpeData2";//URL to PE adoption
			List<CpeDao> list = service.findAll();
			rt.postForEntity(url, list, List.class);
		} catch (Exception e) {
			System.out.println("No connecting to: " + "http://localhost:8081/pe");
		}
	}
}