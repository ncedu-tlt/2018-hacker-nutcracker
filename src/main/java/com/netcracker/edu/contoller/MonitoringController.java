package com.netcracker.edu.contoller;

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

	@PostMapping ( "/saveCpe" )
	public void saveCpe (@RequestBody CpeDao cpeDao) {
		cpePeService.saveCpe(cpeDao);
	}

	@PostMapping ( "/savePe" )
	public void savePe (@RequestBody PeDao peDao) {
		cpePeService.savePe(peDao);
	}

	@GetMapping ( "/deleteCpe/{ip}" )
	public void deleteCpe (@PathVariable("ip") String ip) {
		cpePeService.deleteCpe(ip);
	}

	@GetMapping ( "/deletePe/{ip}" )
	public void deletePe (@PathVariable("ip") String ip) {
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

	public CpeDao getCpeByIp (String ip) {
		return cpePeService.getCpeByIp(ip);
	}

	public PeDao getPeByIp (String ip) {
		return cpePeService.getPeByIp(ip);
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
		CpeDao cpe = getCpeByIp(ip);
		PeDao pe = getPeByIp(ip);
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
			}catch (Exception e) {
				pe.setCoordinateX(intX);
				pe.setCoordinateY(intY);
				savePe(pe);
			}
		}
	}

	@GetMapping ( "/message" )
	public String message ( ) {
		return pagesController.tempMassage();
	}

	@GetMapping ( "/lines" )
	public String lines ( ) {
		return pagesController.lines();
	}
}