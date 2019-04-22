package com.netcracker.edu.contoller;

import com.google.gson.Gson;
import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.service.CpePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

	private HashMap<Integer, String> peLinks = new HashMap<>();
	private HashMap<Integer, String> cpeLinks = new HashMap<>();

	public void setLinksDto (HashMap<Integer, String> peLinks, HashMap<Integer, String> cpeLinks) {
		this.peLinks = peLinks;
		this.cpeLinks = cpeLinks;
	}

	public HashMap<Integer, String> getPeLinks ( ) {
		peLinks.put(1, "http://localhost:8081/pe/add");
		peLinks.put(2, "http://localhost:8081/pe/delete");
		peLinks.put(3, "http://localhost:8081/pe/fan");
		return peLinks;
	}

	public HashMap<Integer, String> getCpeLinks ( ) {
		cpeLinks.put(1, "http://localhost:8080/cpe/add");
		cpeLinks.put(2, "http://localhost:8080/cpe/delete");
		cpeLinks.put(3, "http://localhost:8080/cpe/internet");
		return cpeLinks;
	}

	@GetMapping ( "/addCpe/{cpeStr}" )
	public void addOneCpe (@PathVariable ( "cpeStr" ) String cpeStr) {
		Gson gson = new Gson();
		CpeDao cpe = gson.fromJson(cpeStr, CpeDao.class);
		cpe.setType("CPE");
		cpe.setMaxSpeed(1000);
		cpe.setSpeed(0);
		cpe.setCoordinateX(100);
		cpe.setCoordinateY(100);
		cpePeService.saveCpe(cpe);
	}

	@GetMapping ( "/addPe/{peStr}" )
	public void addOnePe (@PathVariable ( "peStr" ) String peStr) {
		Gson gson = new Gson();
		PeDao pe = gson.fromJson(peStr, PeDao.class);
		pe.setType("PE");
		pe.setMaxSpeed(10000);
		pe.setSpeed(0);
		pe.setTemperature(40);
		pe.setCoordinateX(100);
		pe.setCoordinateY(200);
		pe.setFanActive(false);
		cpePeService.savePe(pe);
	}

	@GetMapping ( "/deleteCpe/{ip}" )
	public void deleteCpe (@PathVariable ( "ip" ) String ip) {
		cpePeService.deleteCpe(ip);
	}

	@GetMapping ( "/deletePe/{ip}" )
	public void deletePe (@PathVariable ( "ip" ) String ip) {
		cpePeService.deletePe(ip);
	}

	@GetMapping ( "/welcome" )
	public ModelAndView welcomePage ( ) {
		ModelAndView model = new ModelAndView("WelcomePage");
		model.addObject("linksCpe", getCpeLinks());
		model.addObject("linksPe", getPeLinks());
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

	@GetMapping ( "/refreshXY/{ip}/{x}/{y}" )
	public void refreshXY (@PathVariable ( "ip" ) String ip, @PathVariable ( "x" ) String x, @PathVariable ( "y" ) String y) {
		CpeDao cpe = cpePeService.getCpeByIp(ip);
		PeDao pe = cpePeService.getPeByIp(ip);
		Integer intX;
		Integer intY;
		try {
			intX = Integer.parseInt(x.substring(0, x.indexOf(".")));
		} catch (Exception e) {
			intX = Integer.parseInt(x);
		}
		try {
			intY = Integer.parseInt(y.substring(0, y.indexOf(".")));
		} catch (Exception e) {
			intY = Integer.parseInt(y);
		}
		if (intX > 0 && intY > 0) {
			try {
				cpe.setCoordinateX(intX);
				cpe.setCoordinateY(intY);
				saveCpe(cpe);
			} catch (Exception e) {
				pe.setCoordinateX(intX);
				pe.setCoordinateY(intY);
				savePe(pe);
			}
		}
	}

	@GetMapping ( "/lines" )
	public String lines ( ) {
		return pagesController.lines();
	}

	private void saveCpe (@RequestBody CpeDao cpeDao) {
		cpePeService.saveCpe(cpeDao);
	}

	private void savePe (@RequestBody PeDao peDao) {
		cpePeService.savePe(peDao);
	}
}