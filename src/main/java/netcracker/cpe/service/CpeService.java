package netcracker.cpe.service;

import netcracker.cpe.entity.dao.CpeDao;
import netcracker.cpe.entity.dao.CpeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CpeService {

	@Autowired
	CpeRepository cpeRepository;

	public CpeDao getCpeByIp (String ip) {
		return cpeRepository.getOneByIp(ip);
	}


	public void saveCpe (CpeDao cpe) {
		cpeRepository.save(cpe);
	}

	public void deleteCpe (String ip) {
		cpeRepository.deleteById(ip);
	}

	public List<CpeDao> findAll ( ) {
		return cpeRepository.findAll();
	}

	public List<CpeDao> findAllByPeIpAddressAndIsInternetActive (String peIp) {
		return cpeRepository.findAllByPeIpAddressAndIsInternetActive(peIp, true);
	}
}
