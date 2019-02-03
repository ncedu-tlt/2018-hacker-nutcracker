package com.netcracker.edu.controller;
import com.netcracker.edu.database.DBQueries;
import com.netcracker.edu.database.OracleDriverManager;
import com.netcracker.edu.Person;
import com.netcracker.edu.Model;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public abstract class AbstractController {

	protected Integer wayId;
	protected Model model = new Model();
	protected DBQueries database = DBQueries.getInstance(getOracleConnection());

	protected Connection getOracleConnection() {
		OracleDriverManager driverManager = new OracleDriverManager();
		return driverManager.openOracleConnection();
	}

	protected void addPersonInDB(Person person) {
		database.insertPersonWay(person);
	}
	protected void addPersonInDB_wayIsExist(Person person, int wayId) {
		database.insertPerson(person, wayId);
	}

	protected void addPerson(Person person) {
		if (!isPersonIdExist(person.getId())) {
			if (isWayExist(person.getWay()) == null){
				addPersonInDB(person);
			} else {
				addPersonInDB_wayIsExist(person, wayId);
			}
		}
	}

	protected void changePersonInDB(Person person) {
		database.updatePersonWay(person);
	}
	protected void changePersonInDB_wayIsExist(Person person, int wayId) {
		database.updatePerson(person, wayId);
	}

	protected void deletePersonInDB(int persId) {
		database.delete(persId);
	}

	protected ArrayList getAllPersons() {
		return model.getAllPersons(database.selectAll());
	}
	public Person getPerson(Integer id) {
		return model.getPerson(database.selectId(id));
	}

	protected Integer isWayExist(String way) {
		return wayId = model.theWayId(database.findWay(way));
	}

	protected boolean isPersonIdExist(Integer id) {
		return model.isExistPersonId(database.selectId(id), id);
	}
}