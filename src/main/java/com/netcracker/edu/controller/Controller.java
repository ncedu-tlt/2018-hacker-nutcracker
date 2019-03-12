package com.netcracker.edu.controller;

import com.netcracker.edu.Model;
import com.netcracker.edu.Person;
import com.netcracker.edu.database.DBQueries;
import com.netcracker.edu.database.OracleDriverManager;

import java.sql.Connection;
import java.util.ArrayList;

public class Controller implements PersonHelper {

	private XMLHelper xml = new XMLHelper();
	private CSVHelper csv = new CSVHelper();
	private JSONHelper json = new JSONHelper();
	private DBQueries database = DBQueries.getInstance(getOracleConnection());
	private Model model = new Model();
	protected Integer wayId;

	private Connection getOracleConnection() {
		OracleDriverManager driverManager = new OracleDriverManager();
		return driverManager.openOracleConnection();
	}

	@Override
	public void createPerson(Person person)  {
		if (!isPersonIdExist(person.getId())) {
			if (isWayExist(person.getWay()) == null){
				addPersonInDB(person);
			} else {
				addPersonInDB_wayIsExist(person, wayId);
			}
		}
	}

	@Override
	public void changePerson(Person person) {
		if (isWayExist(person.getWay()) == null){
			changePersonInDB(person);
		} else {
			changePersonInDB_wayIsExist(person, wayId);
		}
	}

	@Override
	public void deletePerson(int persId) {
		database.delete(persId);
	}

	@Override
	public ArrayList getAllPerson() {
		return model.getAllPersons(database.selectAll());
	}

	@Override
	public Person getPerson(Integer id) {
		return model.getPerson(database.selectId(id));
	}

	@Override
	public void parseFilesInDir(String path) { xml.parseFilesInDir(path); }

	@Override
	public void parseFile(String path, String nameFile) {
		xml.parseFile(path, nameFile);
	}

	public void savePersonInFile(Person person, String format)  {
		switch (format){
			case("xml"):{
				xml.createFileXML(person);
			}break;
			case("csv"):{
				csv.createFileCSV(person);
			}break;
			case("json"):{
				json.createFileJSON(person);
			}
		}
	}
	private Integer isWayExist(String way) {
		return wayId = model.theWayId(database.findWay(way));
	}
	private boolean isPersonIdExist(Integer id) {
		return model.isExistPersonId(database.selectId(id), id);
	}
	public ArrayList getAllWays() {
		return model.getAllWays(database.selectWays());
	}
	private void addPersonInDB(Person person) {
		database.insertPersonWay(person);
	}
	private void addPersonInDB_wayIsExist(Person person, int wayId) {
		database.insertPerson(person, wayId);
	}
	private void changePersonInDB(Person person) {
		database.updatePersonWay(person);
	}
	private void changePersonInDB_wayIsExist(Person person, int wayId) {
		database.updatePerson(person, wayId);
	}
}