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

import java.util.*;

@RestController
@RequestMapping ( "/service" )
public class MonitoringController {

	@Autowired
	CpePeService cpePeService;

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

	@PostMapping ( "/allCpe" )
	public List<CpeDao> getAllCpe ( ) {
		return cpePeService.findAllCpe();
	}

	@PostMapping ( "/allPe" )
	public List<PeDao> getAllPe ( ) {
		return cpePeService.findAllPe();
	}

	@GetMapping ( "/welcome" )
	public ModelAndView welcomePage ( ) {

		return new ModelAndView("WelcomePage");
	}

	@ModelAttribute("CpeDaoList")
	public List<CpeDao> cpeDao(){
		return cpePeService.findAllCpe();
	}

	@ModelAttribute("PeDaoList")
	public List<PeDao> PeDao(){
		return cpePeService.findAllPe();
	}

	@ModelAttribute("PeLinksMap")
	public HashMap<Integer, String> peLinks(){
		peLinks = new HashMap<>();
		peLinks.put(1,"123");
		peLinks.put(2,"1234");
		peLinks.put(3,"12345");
		return peLinks;
	}

	@ModelAttribute("CpeLinksMap")
	public HashMap<Integer, String> cpeLinks(){
		cpeLinks = new HashMap<>();
		cpeLinks.put(1,"123");
		cpeLinks.put(2,"1234");
		cpeLinks.put(3,"12345");
		return cpeLinks;
	}
	@GetMapping ( "/getAllLists" )
	public String test () {
		List<PeDao> listPe = getAllPe();
		String str = "<div class=\"divPe\">";
		for (PeDao pe:listPe){
			str+= " <a href=\"#\" class=\"list-group-item list-group-item-action\">"+ pe.getIp()+ "</a>"+
					" <a href=\"#\" class=\"list-group-item list-group-item-action\">"+ pe.getTemperature()+ "</a>"+
					" <a href=\"#\" class=\"list-group-item list-group-item-action\">"+ pe.getDownlinkSpeed()+ "</a>";
		}
		str+="</div>";
		return str;
	}
}

