package com.netcracker.edu.model.repository;

import com.netcracker.edu.model.dao.PersonDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonDao, Integer> {
	List<PersonDao> findAllByOrderByIdAsc();

}
