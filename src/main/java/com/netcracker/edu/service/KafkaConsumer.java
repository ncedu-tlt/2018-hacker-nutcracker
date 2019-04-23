package com.netcracker.edu.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.netcracker.edu.contoller.MonitoringController;
import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dto.CpeDto;
import com.netcracker.edu.entity.dto.PeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class KafkaConsumer {

	@Autowired
	MonitoringController monitoringController;

	@Autowired
	CpePeService cpePeService;

	private HashMap<Integer, String> peLinks = new HashMap<>();
	private HashMap<Integer, String> cpeLinks = new HashMap<>();
	private Type listCpeType = new TypeToken<ArrayList<CpeDto>>() {
	}.getType();
	private Type listPeType = new TypeToken<ArrayList<PeDto>>() {
	}.getType();

	@KafkaListener ( topics = "total_topic" )
	public void consume (String message) {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		if (message.contains("fanActive")) {
			List<PeDao> listPe = cpePeService.findAllPe();
			List<PeDto> listPeFromMess = gson.fromJson(message, listPeType);
			savePe(listPeFromMess, listPe);
		} else if (message.contains("internetActive")) {
			List<CpeDao> listCpe = cpePeService.findAllCpe();
			List<CpeDto> listCpeFromMess = gson.fromJson(message, listCpeType);
			saveCpe(listCpeFromMess, listCpe);
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

	public void savePe (List<PeDto> listPeFromMess, List<PeDao> listPe) {
		boolean isFind = false;
		for (PeDto peDto : listPeFromMess) {
			for (PeDao peDao : listPe) {
				if (peDao.getIp().equals(peDto.getIp())) {
					peDao.setFanActive(peDto.isFanActive());
					peDao.setSpeed(peDto.getSpeed());
					peDao.setTemperature(peDto.getTemperature());
					cpePeService.savePe(peDao);
					isFind = true;
					break;
				}
			}
			if (!isFind) {
				PeDao pe = new PeDao();
				pe.setIp(peDto.getIp());
				pe.setCoordinateX(peDto.getCoordinateX());
				pe.setCoordinateY(peDto.getCoordinateY());
				pe.setFanActive(peDto.isFanActive());
				pe.setMaxSpeed(peDto.getMaxSpeed());
				pe.setSpeed(peDto.getSpeed());
				pe.setTemperature(peDto.getTemperature());
				pe.setType(peDto.getType());
				cpePeService.savePe(pe);
			}
			isFind = false;
		}
	}

	public void saveCpe (List<CpeDto> listCpeFromMess, List<CpeDao> listCpe) {
		boolean isFind = false;
		for (CpeDto cpeDto : listCpeFromMess) {
			for (CpeDao cpeDao : listCpe) {
				if (cpeDao.getIp().equals(cpeDto.getIp())) {
					cpeDao.setInternetActive(cpeDto.isInternetActive());
					cpeDao.setSpeed(cpeDto.getSpeed());
					cpePeService.saveCpe(cpeDao);
					isFind = true;
					break;
				}
			}
			if (!isFind) {
				CpeDao cpe = new CpeDao();
				cpe.setIp(cpeDto.getIp());
				cpe.setCoordinateX(cpeDto.getCoordinateX());
				cpe.setCoordinateY(cpeDto.getCoordinateY());
				cpe.setInternetActive(cpeDto.isInternetActive());
				cpe.setMaxSpeed(cpeDto.getMaxSpeed());
				cpe.setSpeed(cpeDto.getSpeed());
				cpe.setType(cpeDto.getType());
				cpe.setPeIpAddress(cpeDto.getPeIpAddress());
				cpePeService.saveCpe(cpe);
			}
			isFind = false;
		}
	}
}