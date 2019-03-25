package com.netcracker.edu.conversion;

import com.netcracker.edu.model.dao.PersonDao;
import com.netcracker.edu.model.dto.PersonDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonDaoToPersonDtoConverter implements Converter<PersonDao, PersonDto> {

	@Override
	public PersonDto convert (PersonDao personDao) {
		PersonDto personDto = new PersonDto();
		personDto.setId(personDao.getId());
		personDto.setName(personDao.getName());
		personDto.setWay(personDao.getWay().getName());
		personDto.setUSD(personDao.getUSD());
		return personDto;
	}
}
