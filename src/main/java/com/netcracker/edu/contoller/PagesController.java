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

	@Autowired
	private MonitoringController monitoringController;

	public String refreshListPe ( ) {
		List<PeDao> listPe = cpePeService.findAllPe();
		String str = "";
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
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		String str = "";
		for (CpeDao cpe : listCpe) {

			str +=  " <div class=\"accordion\" id=\"accordionExample\">\n" +
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
		List<PeDao> listPe = cpePeService.findAllPe();
		List<CpeDao> listCpe = cpePeService.findAllCpe();

		String str = "";

		for (int i = 0; i < listPe.size(); i++) {
			str += " <div class=\"item two \" style=\"position: absolute; top: " + listPe.get(i).getCoordinateX() + "px;" +
					"left: " + listPe.get(i).getCoordinateY() + "px; z-index: 10\" id=\"" + listPe.get(i).getIp() + "\">\n" +

					"                <div class=\"descrPE\" name=\"test1\" style=\"display : none\">\n" +
					"                    <p class=\"pcl\">" + "IP:          " + listPe.get(i).getIp() + "</p>\n" +
					"                    <p class=\"pcl\">" + "Speed:       " + listPe.get(i).getDownlinkSpeed() + " kb/s</p>\n" +
					"                    <p class=\"pcl\">" + "Temperature: " + listPe.get(i).getTemperature() + " C </p>\n";
			if (listPe.get(i).isFanActive()) {
				str += "                    <p class=\"pcl\">" + "Fan:         " + "<span class=\"badge badge-pill badge-success\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			} else {
				str += "                    <p class=\"pcl\">" + "Fan:         " + "<span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			}
			str +=                    "<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changeCpeInternet('"+ monitoringController.getPeLinks().get(3) + "/" + listPe.get(i).getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deleteCpe('" + monitoringController.getPeLinks().get(2) + "/" + listPe.get(i).getIp() + "')\">Удалить\n" +
					"                    </button>" ;
			str +=
					"                </div>\n" +
							"            </div>";
		}

		for (int i = 0; i < listCpe.size(); i++) {
			str += " <div class=\"item three \" style=\"position: absolute; top: " + listCpe.get(i).getCoordinateX() + "px;" +
					"left: " + listCpe.get(i).getCoordinateY() + "px;\" id=\"" + listCpe.get(i).getIp() + "\">\n" +
					"                <div class=\"descrCPE\" name=\"test2\">\n" +
					"                    <p class=\"pcl\">" + "IP:          " + listCpe.get(i).getIp() + "</p>\n" +
					"                    <p class=\"pcl\">" + "Speed:       " + listCpe.get(i).getDownlinkSpeed() + " kb/s</p>\n";
			if (listCpe.get(i).isInternetActive()) {
				str += "                    <p class=\"pcl\">" + "Internet: " + "<span class=\"badge badge-pill badge-success\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			} else {
				str += "                    <p class=\"pcl\">" + "Internet: " + "<span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" + "</p>\n";
			}
			str +=                            "<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-info\" onclick=\"changeCpeInternet('" + monitoringController.getCpeLinks().get(3) + "/" + listCpe.get(i).getIp() + "')\">Вкл/Выкл\n" +
					"                    </button>" +
					"<button style=\"margin-left: 5px;\" type=\"submit\" class=\"btn btn-danger\" onclick=\"deleteCpe('" + monitoringController.getCpeLinks().get(2) + "/" + listCpe.get(i).getIp() + "')\">Удалить\n" +
					"                    </button>" ;
			str +=
					"               </div>\n" +
							"            </div>";
		}
		return str;

	}

	public String tempMassage ( ) {
		String str = "";
		List<PeDao> listPe= cpePeService.findAllPe();
		for (int i = 0; i < listPe.size(); i++) {
			if (listPe.get(i).getTemperature() > 85) {
				str += "<div class=\"toast\" role=\"alert\" aria-live=\"assertive\" aria-atomic=\"true\">\n" +
						"  <div class=\"toast-header\">\n" +
						"    <span class=\"badge badge-pill badge-danger\"><span style=\"visibility: hidden\">.</span></span>" +
						"    <strong class=\"mr-auto\">Температура!</strong>\n" +
						"    <small class=\"text-muted\"></small>\n" +
						"    <button type=\"button\" class=\"ml-2 mb-1 close\" data-dismiss=\"toast\" aria-label=\"Close\">\n" +
						"      <span aria-hidden=\"true\">&times;</span>\n" +
						"    </button>\n" +
						"  </div>\n" +
						"  <div class=\"toast-body\">\n" +
						"   " + "ВНИМАНИЕ! Температура на PE по адрессу: " + listPe.get(i).getIp() + " приближается к критической отметке, включите охлаждение. \n" +
						"  </div>\n" +
						"</div>";
			}
		}
		return str;
	}

	public String lines() {
		List<PeDao> listPe = cpePeService.findAllPe();
		List<CpeDao> listCpe = cpePeService.findAllCpe();
		String str="";
		for (int j=0; j< listPe.size();j++) {
			for (int i = 0; i < listCpe.size(); i++) {
				if(listPe.get(j).getIp().equals(listCpe.get(i).getPeIpAddress())) {
					str += "<line x1=\"" + (listPe.get(j).getCoordinateY()+25) + "\" y1=\"" + (listPe.get(j).getCoordinateX()+50) + "\" x2=\""
							+ (listCpe.get(i).getCoordinateY()+25) + "\" y2=\"" + (listCpe.get(i).getCoordinateX()+50) + "\" style=\"stroke:rgb(255,0,0);stroke-width:2\" />\n";
				}
			}
		}
		return str;
	}
}
