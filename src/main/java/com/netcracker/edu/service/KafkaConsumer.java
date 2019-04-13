package com.netcracker.edu.service;

import com.google.gson.Gson;
import com.netcracker.edu.contoller.MonitoringController;
import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dto.LinksDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringTokenizer;

@Service
public class KafkaConsumer {

	@Autowired
	MonitoringController monitoringController;

	private LinksDto linksDto;

	@KafkaListener ( topics = "total_topic" )
	public void consume (String message) {
		StringTokenizer st = new StringTokenizer(message, "[]");

		List<PeDao> listPe = new Gson().fromJson(("[" + st.nextToken() + "]"), List.class);
		String[] linksPe = st.nextToken().split(",");
		linksDto.getPeLinks().put(1, linksPe[0].substring(1));
		linksDto.getPeLinks().put(2, linksPe[1]);
		linksDto.getPeLinks().put(3, linksPe[2].substring(0, linksPe[2].length() - 1));

		List<CpeDao> listCpe = new Gson().fromJson(("[" + st.nextToken() + "]"), List.class);
		String[] linksCpe = st.nextToken().split(",");
		linksDto.getCpeLinks().put(1, linksCpe[0].substring(1));
		linksDto.getCpeLinks().put(2, linksCpe[1]);
		linksDto.getCpeLinks().put(3, linksCpe[2].substring(0, linksPe[2].length() - 1));

		listPe.forEach(peDao -> monitoringController.savePe(peDao));
		listCpe.forEach(cpeDao -> monitoringController.saveCpe(cpeDao));
		monitoringController.setLinksDto(linksDto.getPeLinks(), linksDto.getCpeLinks());
	}
}