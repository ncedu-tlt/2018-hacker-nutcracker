package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.service.CpePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagesController {

	@Autowired
	private CpePeService cpePeService;

	public String refreshListPe ( ) {
		List<PeDao> listPe = cpePeService.findAllPe();
		String str = "<div class=\"divPe\">";
		for (PeDao pe : listPe) {
			str += " <a href=\"#\" class=\"list-group-item list-group-item-action\" id=\"deletePeIp\">" + pe.getIp() + "</a>";
		}
		str += "</div>";
		return str;
	}

	public String refreshListCpe ( ) {
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		String str = "<div class=\"divCpe\">";
		for (CpeDao cpe : listCpe) {
			str += " <a href=\"#\" class=\"list-group-item list-group-item-action\" id=\"deleteCpeIp\">" + cpe.getIp() + "</a>";
		}
		str += "</div>";
		return str;
	}

	public String refreshPeAndCpe ( ) {
		List<PeDao> listPe = cpePeService.findAllPe();
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		String str = "";

		for (int i = 0; i < listPe.size(); i++) {
			str += "<div class=\"item two \" style=\"position: absolute; top: " + listPe.get(i).getCoordinateX() + "px;" +
					"left: " + listPe.get(i).getCoordinateY() + "px; id=\"block1\">\n" +

					"                <div class=\"descr\">\n" +
					"                    <p>" + "IP:          " + listPe.get(i).getIp() + "</p>\n" +
					"                    <p>" + "Speed:       " + listPe.get(i).getDownlinkSpeed() + " kb/s" + "</p>\n" +
					"                    <p>" + "Temperature: " + listPe.get(i).getTemperature() + " C" + "</p>\n" +
					"                    <p>" + "Fan:         " + listPe.get(i).isFanActive() + "</p>\n" +
					"                </div>\n" +
					"            </div>";
		}

		for (int i = 0; i < listCpe.size(); i++) {
			str += " <div class=\"item three \" style=\"position: absolute; top: " + listCpe.get(i).getCoordinateX() + "px;" +
					"left: " + listCpe.get(i).getCoordinateY() + "px;id=\"sblock2\" id=\"" + listCpe.get(i).getIp() + "\">\n" +
					"                <div class=\"descr\">\n" +
					"                    <p>" + "IP:          " + listCpe.get(i).getIp() + "</p>\n" +
					"                    <p>" + "Speed:       " + listCpe.get(i).getDownlinkSpeed() + " kb/s" + "</p>\n" +
					"                    <p>" + "Internet:    " + listCpe.get(i).isInternetActive() + "</p>\n" +
					"                </div>\n" +
					"            </div>";
		}
		return str;
	}
}
