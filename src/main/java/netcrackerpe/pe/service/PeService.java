package netcrackerpe.pe.service;

import netcrackerpe.pe.entity.dao.PeDao;
import netcrackerpe.pe.repository.PeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PeService {
    @Autowired
    private PeRepository peRepository;

    public PeDao getPeByIp(String ip) {

        return peRepository.getOneByIp(ip);
    }

    public void savePe(PeDao pe) {
        peRepository.save(pe);
    }

    public void deletePe(String ip) {
        peRepository.deleteById(ip);
    }

    public List<PeDao> findAll() {
        return peRepository.findAll();
    }
}
