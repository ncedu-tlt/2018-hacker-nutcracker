package com.netcracker.edu.kafka;

import com.netcracker.edu.model.dto.PersonDto;

public class KafkaParserFile {

	public PersonDto parserXML (String message) {

		PersonDto personDto = new PersonDto();

		String[] word = message.split("id");
		StringBuffer sb = new StringBuffer(word[1]);
		sb.delete(0, 1);
		int lenght = sb.length();
		sb.delete(lenght - 2, lenght);
		personDto.setId(Integer.parseInt(sb.toString()));

		word = message.split("name");
		sb = new StringBuffer(word[1]);
		sb.delete(0, 1);
		lenght = sb.length();
		sb.delete(lenght - 2, lenght);
		personDto.setName(sb.toString());

		word = message.split("way");
		sb = new StringBuffer(word[1]);
		sb.delete(0, 1);
		lenght = sb.length();
		sb.delete(lenght - 2, lenght);
		personDto.setWay(sb.toString());

		word = message.split("USD");
		sb = new StringBuffer(word[1]);
		sb.delete(0, 1);
		lenght = sb.length();
		sb.delete(lenght - 2, lenght);
		personDto.setUSD(Integer.parseInt(sb.toString()));

		return personDto;
	}

	public PersonDto parserCSV (String message) {

		PersonDto personDto = new PersonDto();
		String[] word = message.split("\"");
		personDto.setId(Integer.parseInt(word[9]));
		personDto.setName(word[11]);
		personDto.setWay(word[13]);
		personDto.setUSD(Integer.parseInt(word[15]));
		return personDto;

	}

	public PersonDto parserJSON (PersonDto personDto) {
		return personDto;
	}
}
