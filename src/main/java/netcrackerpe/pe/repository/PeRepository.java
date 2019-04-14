package netcrackerpe.pe.repository;

import netcrackerpe.pe.entity.dao.PeDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeRepository extends JpaRepository<PeDao, String> {

    PeDao getOneByIp(String ip);
}
