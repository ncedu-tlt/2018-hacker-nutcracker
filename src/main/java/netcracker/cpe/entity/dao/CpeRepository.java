package netcracker.cpe.entity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpeRepository extends JpaRepository<CpeDao, String> {

	CpeDao getOneByIp (String ip);

	List<CpeDao> findAllByPeIpAddressAndIsInternetActive (String peIp, boolean internet);
}
