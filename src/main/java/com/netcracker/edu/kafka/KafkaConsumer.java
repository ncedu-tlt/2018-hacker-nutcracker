package com.netcracker.edu.kafka;

import com.netcracker.edu.model.dao.PersonDao;
import com.netcracker.edu.model.dto.PersonDto;
import com.netcracker.edu.model.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	private KafkaParserFile kafka = new KafkaParserFile();

	@Autowired
	PersonRepository personRepository;
	@Autowired
	private ConversionService conversionService;

	@KafkaListener(topics = "javainuse-topic-csv")
	public void consumeCSV(String message) {
		PersonDto personDto = kafka.parserCSV(message);
		PersonDao personDao = conversionService.convert(personDto, PersonDao.class);
		personRepository.save(personDao);
	}

	@KafkaListener(topics = "javainuse-topic-xml")
	public void consumeXML(String message) {
		PersonDto personDto = kafka.parserXML(message);
		PersonDao personDao = conversionService.convert(personDto, PersonDao.class);
		personRepository.save(personDao);
	}

	@KafkaListener(topics = "javainuse-topic-json",
			containerFactory = "userKafkaListenerFactory")
	public void consumeJson(PersonDto person) {
		PersonDto personDto = kafka.parserJSON(person);
		PersonDao personDao = conversionService.convert(personDto, PersonDao.class);
		personRepository.save(personDao);
	}
}