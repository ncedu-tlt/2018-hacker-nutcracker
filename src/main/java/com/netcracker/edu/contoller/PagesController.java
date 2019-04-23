package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.service.CpePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Service
public class PagesController implements Serializable {


	@Autowired
	private CpePeService cpePeService;

	@Autowired
	private MonitoringController monitoringController;

	private int kindOfSwitch = 0;

	public String refreshListPe ( ) {
		String str = "";
		List<PeDao> listPe = cpePeService.findAllPe();
		for (PeDao pe : listPe) {
			str += " <div class=\"accordion\" id=\"accordionExample\">\n" +
					"  <div class=\"card\">\n" +
					"    <div class=\"card-header\" id=\"headingTwo\">\n" +
					"      <h5 class=\"mb-0\">\n" +
					"        <button class=\"btn btn-link\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseTwo\" aria-expanded=\"false\" aria-controls=\"collapseTwo\">\n" +
					"          " + pe.getIp() + "\n" +
					"        </button>\n" +
					"<button style=\"margin-left: 120px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changePeInternet('" + monitoringController.getPeLinks().get(3) + "/" + pe.getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 20px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deletePe('" + monitoringController.getPeLinks().get(2) + "/" + pe.getIp() + "')\">Удалить\n" +
					"                    </button>" +
					"      </h5>\n" +
					"    </div>" +
					"</div>" +
					"</div>" +
					"</div>";
		}
		return str;
	}

	public String refreshListCpe ( ) {
		String str = "";
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		for (CpeDao cpe : listCpe) {

			str += " <div class=\"accordion\" id=\"accordionExample\">\n" +
					"  <div class=\"card\">\n" +
					"    <div class=\"card-header\" id=\"headingTwo\">\n" +
					"      <h5 class=\"mb-0\">\n" +
					"        <button class=\"btn btn-link\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapseTwo\" aria-expanded=\"false\" aria-controls=\"collapseTwo\">\n" +
					"          " + cpe.getIp() + "\n" +
					"        </button>\n" +
					"<button style=\"margin-left: 120px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changeCpeInternet('" + monitoringController.getCpeLinks().get(3) + "/" + cpe.getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 20px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deleteCpe('" + monitoringController.getCpeLinks().get(2) + "/" + cpe.getIp() + "')\">Удалить\n" +
					"                    </button>" +
					"      </h5>\n" +
					"    </div>" +
					"</div>" +
					"</div>" +
					"</div>";
		}
		return str;
	}

	public String refreshPeAndCpe ( ) {
		String str = "";
		List<PeDao> listPe = cpePeService.findAllPe();
		List<CpeDao> listCpe = cpePeService.findAllCpe();

		for (int i = 0; i < listPe.size(); i++) {
			str += " <div class=\"item two \" style=\"position: absolute; top: " + listPe.get(i).getCoordinateX() + "px;" +
					"left: " + listPe.get(i).getCoordinateY() + "px; z-index: 10\" id=\"" + listPe.get(i).getIp() + "\">\n" +

					"                <div class=\"descrPE descr\" name=\"test1\">\n" +
					"                    <p class=\"pcl\">" + "IP:          " + listPe.get(i).getIp() + "</p>\n" +
					"                    <p class=\"pcl\">" + "Speed:       " + listPe.get(i).getSpeed() + " kb/s</p>\n" +
					"                    <p class=\"pcl\">" + "Temperature: " + listPe.get(i).getTemperature() + " C </p>\n";
			if (listPe.get(i).isFanActive()) {
				str += "                    <p class=\"pcl\">" + "Fan:         " + "<span class=\"badge badge-pill badge-success\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			} else {
				str += "                    <p class=\"pcl\">" + "Fan:         " + "<span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			}
			str += "<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changePeFan('" + monitoringController.getPeLinks().get(3) + "/" + listPe.get(i).getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deletePe('" + monitoringController.getPeLinks().get(2) + "/" + listPe.get(i).getIp() + "')\">Удалить\n" +
					"                    </button>" +
					"                </div>\n" +
					"            </div>";
		}

		for (int i = 0; i < listCpe.size(); i++) {
			str += " <div class=\"item three \" style=\"position: absolute; top: " + listCpe.get(i).getCoordinateX() + "px;" +
					"left: " + listCpe.get(i).getCoordinateY() + "px;\" id=\"" + listCpe.get(i).getIp() + "\">\n" +
					"                <div class=\"descrCPE descr\" name=\"test2\">\n" +
					"                    <p class=\"pcl\">" + "IP:          " + listCpe.get(i).getIp() + "</p>\n" +
					"                    <p class=\"pcl\">" + "Speed:       " + listCpe.get(i).getSpeed() + " kb/s</p>\n";
			if (listCpe.get(i).isInternetActive()) {
				str += "                    <p class=\"pcl\">" + "Internet: " + "<span class=\"badge badge-pill badge-success\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			} else {
				str += "                    <p class=\"pcl\">" + "Internet: " + "<span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			}
			str += "<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changeCpeInternet('" + monitoringController.getCpeLinks().get(3) + "/" + listCpe.get(i).getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deleteCpe('" + monitoringController.getCpeLinks().get(2) + "/" + listCpe.get(i).getIp() + "')\">Удалить\n" +
					"                    </button>" +
					"               </div>\n" +
					"            </div>";
		}
		return str;

	}

	public String lines ( ) {

		List<PeDao> listPe = cpePeService.findAllPe();
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		HashMap<Integer, String> colors = new HashMap<>(7);
		String str = "";
		colors.put(0, "#323433");
		colors.put(1, "#C300AE");
		colors.put(2, "#C30008");
		colors.put(3, "#00C60B");
		colors.put(4, "#0100C4");
		colors.put(5, "#24657A");
		colors.put(6, "#C37C00");
		for (int j = 0; j < listPe.size(); j++) {
			for (int i = 0; i < listCpe.size(); i++) {
				if (listPe.get(j).getIp().equals(listCpe.get(i).getPeIpAddress())) {
					switch (kindOfSwitch) {
						case 0: {
							str += "<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 40px\"  />\n";
							kindOfSwitch++;
						}
						break;
						case 1: {
							str += "<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 35px\" />\n";
							kindOfSwitch++;
						}
						break;
						case 2: {
							str += "<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 30px\" />\n";
							kindOfSwitch++;
						}
						break;
						default: {
							str += "<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 25px\" />\n";
							kindOfSwitch = 0;
						}
					}
				}
			}
		}
		return str;
	}
}