package com.netcracker.edu.service;

import com.netcracker.edu.entity.dao.CpeDao;
import com.netcracker.edu.entity.dao.CpeRepository;
import com.netcracker.edu.entity.dao.PeDao;
import com.netcracker.edu.entity.dao.PeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CpePeService {

	@Autowired
	CpeRepository cpeRepository;

	@Autowired
	PeRepository peRepository;

	public void saveCpe (CpeDao cpe) {
		cpeRepository.save(cpe);
	}

	public void deleteCpe (String ip) {
		cpeRepository.deleteById(ip);
	}

	public void savePe (PeDao pe) {
		peRepository.save(pe);
	}

	public void deletePe (String ip) {
		peRepository.deleteById(ip);
	}

	public List<CpeDao> findAllCpe ( ) {
		return cpeRepository.findAll();
	}

	public List<PeDao> findAllPe ( ) {
		return peRepository.findAll();
	}

}
