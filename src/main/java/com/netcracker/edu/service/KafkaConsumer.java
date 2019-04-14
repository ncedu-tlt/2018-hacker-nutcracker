package com.netcracker.edu.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netcracker.edu.contoller.MonitoringController;
import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class KafkaConsumer {

	@Autowired
	MonitoringController monitoringController;

	private HashMap<Integer, String> peLinks = new HashMap<>();
	private HashMap<Integer, String> cpeLinks = new HashMap<>();

//	@KafkaListener ( topics = "total_topic" )
	public void consume (String message) {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		if (message.indexOf("fanActive") != -1) {
			PeDao pe = gson.fromJson(message, PeDao.class);
			monitoringController.savePe(pe);
		} else if (message.indexOf("internetActive") != -1) {
			CpeDao cpe = gson.fromJson(message, CpeDao.class);
			monitoringController.saveCpe(cpe);
		} else if (message.indexOf("8081") != -1) {
			splitLinks(message, peLinks);
		} else {
			splitLinks(message, cpeLinks);
			monitoringController.setLinksDto(peLinks, cpeLinks);
		}
	}

	public void splitLinks (String mes, HashMap<Integer, String> links) {
		String[] arrLinks = mes.split(",");
		links.put(1, arrLinks[0].substring(1));
		links.put(2, arrLinks[1]);
		links.put(3, arrLinks[2].substring(0, arrLinks[2].length() - 1));
	}
}