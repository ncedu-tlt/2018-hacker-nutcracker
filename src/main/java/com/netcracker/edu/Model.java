package com.netcracker.edu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {

	private ArrayList<Person> persons = new ArrayList<>();

	public ArrayList getAllPersons(ResultSet result) {
		try {
			persons.clear();
			while (result.next()) {
				Person person = new Person();
				person.setId(result.getInt(1));
				person.setName(result.getString(2));
				person.setWay(result.getString(3));
				person.setUSD(result.getInt(4));
				persons.add(person);
			}
		} catch (SQLException e) {e.printStackTrace();}
		return persons;
	}

	public Person getPerson(ResultSet result) {
		Person person = new Person();
		try {
			while (result.next()) {
				person.setId(result.getInt(1));
				person.setName(result.getString(2));
				person.setWay(result.getString(3));
				person.setUSD(result.getInt(4));
			}
		} catch (SQLException e) {e.printStackTrace();}
		return person;
	}

	public Integer theWayId(ResultSet result) {
		Integer wayId=null;
		try {
			while (result.next()) {
				wayId = (result.getInt(1));
			}
		} catch (SQLException e) {e.printStackTrace();}
		return wayId;
	}

	public boolean isExistPersonId (ResultSet result, Integer id) {
		try {
			Integer persId;
			while (result.next()) {
				persId = result.getInt(1);
				if (id.equals(persId)) {
					return true;
				}
			}
		} catch (SQLException e) {e.printStackTrace();}
		return false;
	}
}