package com.ncspring.ncspring.repository;

import com.ncspring.ncspring.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByIdOrNameOrWayOrUsd(int id,String name,String way,int usd);

    List<Person> findByName(String name);

    List<Person> findById(int id);
}
