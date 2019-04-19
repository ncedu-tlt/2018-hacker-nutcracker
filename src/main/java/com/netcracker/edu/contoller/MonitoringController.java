package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dto.CpeDto;
import com.netcracker.edu.entity.dto.PeDto;
import com.netcracker.edu.service.CpePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping ( "/service" )
public class MonitoringController {

	@Autowired
	CpePeService cpePeService;
	@Autowired
	private PagesController pagesController;

	private HashMap<Integer, String> peLinks;
	private HashMap<Integer, String> cpeLinks;

	public void setLinksDto (HashMap<Integer, String> peLinks, HashMap<Integer, String> cpeLinks) {
		this.peLinks = peLinks;
		this.cpeLinks = cpeLinks;
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
		String uri = "http://localhost:8081/pe/fan";
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

	@GetMapping ( "/welcome" )
	public ModelAndView welcomePage ( ) {
		ModelAndView model = new ModelAndView("WelcomePage");
		peLinks = new HashMap<>();
		peLinks.put(1, "http://localhost:8081/pe/add");
		cpeLinks = new HashMap<>();
		cpeLinks.put(1, "http://localhost:8080/cpe/add");
		model.addObject("linksCpe", cpeLinks);
		model.addObject("linksPe", peLinks);
		return model;
	}

	@ModelAttribute ( "CpeDaoList" )
	public List<CpeDao> getAllCpe ( ) {
		return cpePeService.findAllCpe();
	}

	@ModelAttribute ( "PeDaoList" )
	public List<PeDao> getAllPe ( ) {
		return cpePeService.findAllPe();
	}

	@GetMapping ( "/getListPe" )
	public String getListPe ( ) {
		return pagesController.refreshListPe();
	}

	@GetMapping ( "/getListCpe" )
	public String getListCpe ( ) {
		return pagesController.refreshListCpe();
	}

	@GetMapping ( "/refreshPeAndCpe" )
	public String refreshPeAndCpe ( ) {
		return pagesController.refreshPeAndCpe();
	}
}