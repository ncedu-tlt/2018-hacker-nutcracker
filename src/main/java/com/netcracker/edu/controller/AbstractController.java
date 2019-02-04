package com.netcracker.edu.controller;
import com.netcracker.edu.Model;
import com.netcracker.edu.Person;
import com.netcracker.edu.database.DBQueries;
import com.netcracker.edu.database.OracleDriverManager;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class AbstractController {

	protected Integer wayId;
	private Model model = new Model();
	private DBQueries database = DBQueries.getInstance(getOracleConnection());

	private Connection getOracleConnection() {
		OracleDriverManager driverManager = new OracleDriverManager();
		return driverManager.openOracleConnection();
	}

	private void addPersonInDB(Person person) {
		database.insertPersonWay(person);
	}
	private void addPersonInDB_wayIsExist(Person person, int wayId) {
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
	protected ArrayList getAllWays() {
		return model.getAllWays(database.selectWays());
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