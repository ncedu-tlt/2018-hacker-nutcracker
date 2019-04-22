package netcrackerpe.pe.controller;

import com.google.gson.Gson;
import netcrackerpe.pe.entity.dao.PeDao;
import netcrackerpe.pe.entity.dto.CpeDto;
import netcrackerpe.pe.service.PeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping ( "/pe" )
public class PeController {

	@Autowired
	private PeService peService;

	@GetMapping ( value = "/all" )
	public List<PeDao> getAll ( ) {
		return peService.findAll();
	}

	@GetMapping ( "/add/{peStr}" )
	public void addOne (@PathVariable ( "peStr" ) String peStr) {
		Gson gson = new Gson();
		PeDao pe = gson.fromJson(peStr, PeDao.class);
		pe.setType("PE");
		pe.setTemperature(40);
		pe.setMaxSpeed(10000);
		pe.setSpeed(0);
		pe.setFanActive(false);
		pe.setCoordinateX(0);
		pe.setCoordinateY(0);
		peService.savePe(pe);
	}

	@GetMapping ( "/delete/{ip}" )
	public void deleteByIp (@PathVariable String ip) {
		peService.deletePe(ip);
	}

	@GetMapping ( "/{ip}" )
	public PeDao getPeByIp (@PathVariable ( "ip" ) String ip) {
		return peService.getPeByIp(ip);
	}

	@GetMapping ( "/fan/{ip}" )
	public @ResponseBody
	ResponseEntity<HttpStatus> changeFanStatus (@PathVariable ( "ip" ) String ip) {
		PeDao peDao = peService.getPeByIp(ip);
		if (peDao.isFanActive()) {
			peDao.setFanActive(false);
		} else {
			peDao.setFanActive(true);
		}
		peService.savePe(peDao);
		return new ResponseEntity(HttpStatus.OK);
	}

	@RequestMapping ( value = "/cpeData2", method = RequestMethod.POST )
	public ResponseEntity adoption (@RequestBody List<CpeDto> list) {

		List<PeDao> listPe = peService.findAll();
		Integer speed = 0;

		for (int i = 0; i < listPe.size(); i++) {
			for (int s = 0; s < list.size(); s++) {
				if (list.get(s).getPeIpAddress().equals(listPe.get(i).getIp())) {
					speed += list.get(s).getSpeed();
				}
			}
			peService.getPeByIp(listPe.get(i).getIp()).setSpeed(speed);
			peService.savePe(peService.getPeByIp(listPe.get(i).getIp()));
			speed = 0;
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping ( "/sendPe" )
	public void sendPe ( ) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "http://localhost:8080/cpe/peData";
			List<PeDao> list = peService.findAll();
			restTemplate.postForEntity(url, list, List.class);
		} catch (Exception e) {
		}
	}
}