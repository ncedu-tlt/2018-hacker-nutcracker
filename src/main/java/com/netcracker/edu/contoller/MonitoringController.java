package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dto.CpeDto;
import com.netcracker.edu.entity.dto.LinksDto;
import com.netcracker.edu.entity.dto.PeDto;
import com.netcracker.edu.service.CpePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping ( "/service" )
public class MonitoringController {

	@Autowired
	CpePeService cpePeService;

	private LinksDto linksDto;

	public void setLinksDto(HashMap<Integer, String> peLinks, HashMap<Integer, String> cpeLinks){
		linksDto.setPeLinks(peLinks);
		linksDto.setCpeLinks(cpeLinks);
	}

	@PostMapping ( "/internet" )
	public void changeInternetOnCpe (@RequestBody CpeDto cpe) {
		RestTemplate rt = new RestTemplate();
		String uri = "http://localhost:8080/cpe/internet";
		rt.postForEntity(uri, cpe, CpeDto.class);
	}

	@PostMapping ( "/fan" )
	public void changeFanOnPe (@RequestBody PeDto pe) {
		RestTemplate rt = new RestTemplate();
		String uri = "http://localhost:8081/pe/fan";//123
		rt.postForEntity(uri, pe, PeDto.class);
	}

	@PostMapping ( "/saveCpe" )
	public void saveCpe (@RequestBody CpeDao cpeDao) {
		cpePeService.saveCpe(cpeDao);
	}


	@PostMapping ( "/savePe" )
	public void savePe (@RequestBody PeDao peDao) {
		cpePeService.savePe(peDao);
	}

	@PostMapping ( "/deleteCpe" )
	public void deleteCpe (@RequestBody CpeDao cpeDao) {
		cpePeService.deleteCpe(cpeDao.getIp());
	}

	@PostMapping ( "/deletePe" )
	public void deletePe (@RequestBody PeDao peDao) {
		cpePeService.deletePe(peDao.getIp());
	}

	@PostMapping ( "/allCpe" )
	public List<CpeDao> getAllCpe ( ) {
		return cpePeService.findAllCpe();
	}

	@PostMapping ( "/allPe" )
	public List<PeDao> getAllPe ( ) {
		return cpePeService.findAllPe();
	}
}

