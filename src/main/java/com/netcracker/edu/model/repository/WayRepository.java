package com.netcracker.edu.model.repository;

import com.netcracker.edu.model.dao.WayDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WayRepository extends JpaRepository<WayDao, Integer> {
	Optional<WayDao> findByName(String name);
}
