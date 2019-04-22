package com.netcracker.edu.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netcracker.edu.contoller.MonitoringController;
import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.CpeRepository;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dto.CpeDto;
import com.netcracker.edu.entity.dto.PeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class KafkaConsumer {

	@Autowired
	MonitoringController monitoringController;

	@Autowired
	CpePeService cpePeService;

	@Autowired
	CpeRepository cpeRepository;

	private HashMap<Integer, String> peLinks = new HashMap<>();
	private HashMap<Integer, String> cpeLinks = new HashMap<>();

	@KafkaListener ( topics = "total_topic" )
	public void consume (String message) {

		List<CpeDao> listCpe = cpePeService.findAllCpe();
		List<PeDao> listPe = cpePeService.findAllPe();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		if (message.contains("fanActive")) {
			PeDto peDto = gson.fromJson(message, PeDto.class);
			savePe(peDto, listPe);
		} else if (message.contains("internetActive")) {
			CpeDto cpeDto = gson.fromJson(message, CpeDto.class);
			saveCpe(cpeDto, listCpe);
		} else if (message.contains("8081")) {
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

	public void savePe (PeDto peDto, List<PeDao> listPe) {
		for (PeDao peDao : listPe) {
			if (peDao.getIp().equals(peDto.getIp())) {
				peDao.setTemperature(peDto.getTemperature());
				peDao.setSpeed(peDto.getSpeed());
				peDao.setFanActive(peDto.isFanActive());
				cpePeService.savePe(peDao);
			}
		}
	}

	public void saveCpe (CpeDto cpeDto, List<CpeDao> listCpe) {
		for (CpeDao cpeDao : listCpe) {
			if (cpeDao.getIp().equals(cpeDto.getIp())) {
				cpeDao.setInternetActive(cpeDto.isInternetActive());
				cpeDao.setSpeed(cpeDto.getSpeed());
				cpePeService.saveCpe(cpeDao);
			}
		}
	}
}