package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.service.CpePeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PagesController implements Serializable {


	@Autowired
	private CpePeService cpePeService;

	@Autowired
	private MonitoringController monitoringController;

	private List<PeDao> listPe = new ArrayList<>();
	private List<CpeDao> listCpe = new ArrayList<>();
	private HashMap<Integer, String> colors = new HashMap<>(7);
	private StringBuffer stringBuffer = new StringBuffer();
	private int kindOfSwitch = 0;

	public String refreshListPe ( ) {
		List<PeDao> listPe = cpePeService.findAllPe();
		for (PeDao pe : listPe) {
			stringBuffer.append(" <div class=\"accordion\" id=\"accordionExample\">\n" +
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
					"</div>");
		}
		return stringBuffer.toString();
	}

	public String refreshListCpe ( ) {
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		for (CpeDao cpe : listCpe) {

			stringBuffer.append(" <div class=\"accordion\" id=\"accordionExample\">\n" +
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
					"</div>");
		}
		return stringBuffer.toString();
	}

	public String refreshPeAndCpe ( ) {
		List<PeDao> listPe = cpePeService.findAllPe();
		List<CpeDao> listCpe = cpePeService.findAllCpe();

		for (int i = 0; i < listPe.size(); i++) {
			stringBuffer.append(" <div class=\"item two \" style=\"position: absolute; top: " + listPe.get(i).getCoordinateX() + "px;" +
					"left: " + listPe.get(i).getCoordinateY() + "px; z-index: 10\" id=\"" + listPe.get(i).getIp() + "\">\n" +

					"                <div class=\"descrPE descr\" name=\"test1\">\n" +
					"                    <p class=\"pcl\">" + "IP:          " + listPe.get(i).getIp() + "</p>\n" +
					"                    <p class=\"pcl\">" + "Speed:       " + listPe.get(i).getSpeed() + " kb/s</p>\n" +
					"                    <p class=\"pcl\">" + "Temperature: " + listPe.get(i).getTemperature() + " C </p>\n");
			if (listPe.get(i).isFanActive()) {
				stringBuffer.append("                    <p class=\"pcl\">" + "Fan:         " + "<span class=\"badge badge-pill badge-success\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n");
			} else {
				stringBuffer.append("                    <p class=\"pcl\">" + "Fan:         " + "<span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n");
			}
			stringBuffer.append("<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changePeFan('" + monitoringController.getPeLinks().get(3) + "/" + listPe.get(i).getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deletePe('" + monitoringController.getPeLinks().get(2) + "/" + listPe.get(i).getIp() + "')\">Удалить\n" +
					"                    </button>" +
					"                </div>\n" +
					"            </div>");
		}

		for (int i = 0; i < listCpe.size(); i++) {
			stringBuffer.append(" <div class=\"item three \" style=\"position: absolute; top: " + listCpe.get(i).getCoordinateX() + "px;" +
					"left: " + listCpe.get(i).getCoordinateY() + "px;\" id=\"" + listCpe.get(i).getIp() + "\">\n" +
					"                <div class=\"descrCPE descr\" name=\"test2\">\n" +
					"                    <p class=\"pcl\">" + "IP:          " + listCpe.get(i).getIp() + "</p>\n" +
					"                    <p class=\"pcl\">" + "Speed:       " + listCpe.get(i).getSpeed() + " kb/s</p>\n");
			if (listCpe.get(i).isInternetActive()) {
				stringBuffer.append("                    <p class=\"pcl\">" + "Internet: " + "<span class=\"badge badge-pill badge-success\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n");
			} else {
				stringBuffer.append("                    <p class=\"pcl\">" + "Internet: " + "<span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n");
			}
			stringBuffer.append("<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changeCpeInternet('" + monitoringController.getCpeLinks().get(3) + "/" + listCpe.get(i).getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deleteCpe('" + monitoringController.getCpeLinks().get(2) + "/" + listCpe.get(i).getIp() + "')\">Удалить\n" +
					"                    </button>" +
					"               </div>\n" +
					"            </div>");
		}
		return stringBuffer.toString();

	}

	public String lines ( ) {

		listPe = cpePeService.findAllPe();
		listCpe = cpePeService.findAllCpe();
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
							stringBuffer.append("<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 40px\"  />\n");
							kindOfSwitch++;
						}
						break;
						case 1: {
							stringBuffer.append("<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 35px\" />\n");
							kindOfSwitch++;
						}
						break;
						case 2: {
							stringBuffer.append("<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 30px\" />\n");
							kindOfSwitch++;
						}
						break;
						default: {
							stringBuffer.append("<line x1=\"" + (listPe.get(j).getCoordinateY() - 25) + "\" y1=\"" + (listPe.get(j).getCoordinateX() + 75) + "\" x2=\""
									+ (listCpe.get(i).getCoordinateY() - 25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX() + 75) + "\" style=\"stroke: " + colors.get(j) + ";stroke-width:3; filter: brightness(0.6)\" stroke-dasharray=\"50px 25px\" />\n");
							kindOfSwitch = 0;
						}
					}
				}
			}
		}
		return stringBuffer.toString();
	}
}