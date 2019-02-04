package com.netcracker.edu.controller;
import com.netcracker.edu.*;

import java.util.ArrayList;

public class Controller implements PersonHelper {

	private XMLHelper xml = new XMLHelper();
	private CSVHelper csv = new CSVHelper();
	private JSONHelper json = new JSONHelper();

	@Override
	public void createPerson(Person person)  {
				xml.addPerson(person);
	}

	public void savePersonInFile(Person person, String format)  {
		switch (format){
			case(".XML"):{
				xml.createFileXML(person);
			}break;
			case(".CSV"):{
				csv.createFileCSV(person);
			}break;
			case(".JSON"):{
				json.createFileJSON(person);
			}
		}
	}

	@Override
	public void changePerson(Person person) {
		xml.changePerson(person);
	}

	@Override
	public void deletePerson(int persId) {
		xml.deletePerson(persId);
	}

	@Override
	public ArrayList getAllPerson() {
		return xml.getAllPersons();
	}

	public ArrayList getAllWays() {
		return xml.getAllWays();
	}
	@Override
	public Person getPerson(int persId) {
		return xml.getPerson(persId);
	}

	@Override
	public void parseFilesInDir(String path) {
		xml.parseFilesInDir(path);
	}

	@Override
	public void parseFile(String path, String nameFile) {
		xml.parseFile(path, nameFile);
	}
}