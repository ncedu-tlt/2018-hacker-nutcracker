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

	public CpeDao getCpeByIp(String ip){return cpeRepository.getOne(ip);}

	public PeDao getPeByIp(String ip){return peRepository.getOne(ip);}

	public void deletePe (String ip) {
		peRepository.deleteById(ip);
	}

	public List<CpeDao> findAllCpe ( ) {
		List<CpeDao> list = cpeRepository.findAll();
		list.sort((o1, o2) -> o1.getIp().compareToIgnoreCase(o2.getIp()));
		return list;
	}

	public List<PeDao> findAllPe ( ) {
		List<PeDao> list = peRepository.findAll();
		list.sort((o1, o2) -> o1.getIp().compareToIgnoreCase(o2.getIp()));
		return list;
	}

}
