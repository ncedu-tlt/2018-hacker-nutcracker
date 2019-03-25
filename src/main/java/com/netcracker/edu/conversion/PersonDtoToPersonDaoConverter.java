package com.netcracker.edu.conversion;

import com.netcracker.edu.model.dao.PersonDao;
import com.netcracker.edu.model.dao.WayDao;
import com.netcracker.edu.model.dto.PersonDto;
import com.netcracker.edu.model.repository.WayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonDtoToPersonDaoConverter implements Converter<PersonDto, PersonDao> {

	@Autowired
	private WayRepository wayRepository;

	@Override
	public PersonDao convert (PersonDto personDto) {
		PersonDao personDao = new PersonDao();
		Optional<WayDao> optional = wayRepository.findByName(personDto.getWay());

		if (optional.isPresent()) {
			personDao.setWay(optional.get());
		} else{
			WayDao wayDao = new WayDao();
			wayDao.setName(personDto.getWay());
			wayRepository.save(wayDao);
			personDao.setWay(wayDao);
		}

		personDao.setId(personDto.getId());
		personDao.setName(personDto.getName());
		personDao.setUSD(personDto.getUSD());
		return personDao;
	}
}
