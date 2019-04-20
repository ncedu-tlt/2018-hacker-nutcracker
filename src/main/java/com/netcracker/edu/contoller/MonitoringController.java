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

	private HashMap<Integer, String> peLinks;
	private HashMap<Integer, String> cpeLinks;

	public void setLinksDto (HashMap<Integer, String> peLinks, HashMap<Integer, String> cpeLinks) {
		this.peLinks = peLinks;
		this.cpeLinks = cpeLinks;
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
			if (cpe != null) {
				cpe.setCoordinateX(intX);
				cpe.setCoordinateY(intY);
				saveCpe(cpe);
			} else {
				pe.setCoordinateX(intX);
				pe.setCoordinateY(intY);
				savePe(pe);
			}
		}
	}
}