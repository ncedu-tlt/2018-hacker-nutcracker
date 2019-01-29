package com.netcracker.edu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.edu.Person;

import java.io.File;
import java.io.IOException;

public class JSONHelper extends AbstractController {

	@Override
	protected String addExtension(String nameFile) {
		if(!nameFile.endsWith(".json")) {
			nameFile = nameFile + ".json";
		}
		return nameFile;
	}

	public void createFileJSON(String path, String nameFile, Person person) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(path + "\\" + nameFile), person);
		}catch (IOException e) {e.printStackTrace();}
	}

	public void addPerson(Person person) {
		super.addPerson(person);
	}
}