package com.netcracker.edu.controller;
import com.netcracker.edu.*;

import java.util.ArrayList;

public class Controller implements PersonHelper {

	private XMLHelper xml = new XMLHelper();
	private CSVHelper csv = new CSVHelper();
	private JSONHelper json = new JSONHelper();

	@Override
	public void createPerson(String path, String nameFile, Person person, int choice)  {
		switch (choice){
			case(1):{
				xml.createFileXML(path, nameFile, person);
				xml.addPerson(person);
			}break;
			case(2):{
				csv.createFileCSV(path, nameFile, person);
				csv.addPerson(person);
			}break;
			case(3):{
				json.createFileJSON(path, nameFile, person);
				json.addPerson(person);
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

	public boolean checkFileInDir(String path, String nameFile){
		return xml.checkFileInDir(path, nameFile);
	}

	public String addExtension(String nameFile, Integer choice){
		switch (choice){
			case(1):{
				return xml.addExtension(nameFile);
			}
			case(2):{
				return csv.addExtension(nameFile);
			}
			case(3):{
				return json.addExtension(nameFile);
			}
			default: return null;
		}
	}
}