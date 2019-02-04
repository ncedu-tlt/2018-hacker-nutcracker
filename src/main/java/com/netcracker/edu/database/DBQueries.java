package com.netcracker.edu.database;
import com.netcracker.edu.Person;

import java.sql.*;

public final class DBQueries {

	private static Connection connection;
	private static volatile DBQueries instance;

	private DBQueries(Connection connection) {
		this.connection = connection;
	}

	public static DBQueries getInstance(Connection connection) {
		if (instance == null) {
			synchronized (DBQueries.class) {
				if (instance == null) {
					instance = new DBQueries(connection);
				}
			}
		}
		return instance;
	}

	public void insertPersonWay(Person person) {
		try {
			String queryForDirections = "INSERT INTO Directions (id, name) VALUES (SEQ_FOR_NUTCRACKER.NEXTVAL, ?)";
			String queryForPersons = "INSERT INTO Persons (id, name, way, USD) VALUES (?, ?, (SELECT ID FROM DIRECTIONS WHERE NAME=?), ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(queryForDirections);
			preparedStatement.setString(1, person.getWay());
			preparedStatement.execute();

			preparedStatement = connection.prepareStatement(queryForPersons);
			preparedStatement.setInt(1, person.getId());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setString(3, person.getWay());
			preparedStatement.setInt(4, person.getUSD());
			preparedStatement.execute();
			System.out.println("--- INSERTED 1 PERSON ---");
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void insertPerson(Person person, Integer wayId) {
		try {
			String queryForPerson = "INSERT INTO Persons (id, name, way, USD) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStatement = connection.prepareStatement(queryForPerson);
			preparedStatement.setInt(1, person.getId());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setInt(3, wayId);
			preparedStatement.setInt(4, person.getUSD());
			preparedStatement.execute();
			System.out.println("--- INSERTED 1 PERSON ---");
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void updatePersonWay(Person person) {
		try {
			String queryForDirections = "INSERT INTO Directions (id, name) VALUES (SEQ_FOR_NUTCRACKER.NEXTVAL, ?)";
			String queryForPerson = "UPDATE Persons SET name = ?, way = (SELECT ID FROM DIRECTIONS WHERE NAME=?), USD = ? WHERE id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(queryForDirections);
			preparedStatement.setString(1, person.getWay());
			preparedStatement.execute();

			preparedStatement = connection.prepareStatement(queryForPerson);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setString(2, person.getWay());
			preparedStatement.setInt(3, person.getUSD());
			preparedStatement.setInt(4, person.getId());

			preparedStatement.execute();
			System.out.println("--- UPDATED 1 PERSON ---");
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void updatePerson(Person person, Integer wayId) {
		try{
			String queryForPerson = "UPDATE Persons SET name = ?, way = ?, USD = ? WHERE id = ?";

			PreparedStatement preparedStatement = connection.prepareStatement(queryForPerson);
			preparedStatement.setString(1, person.getName());
			preparedStatement.setInt(2, wayId);
			preparedStatement.setInt(3, person.getUSD());
			preparedStatement.setInt(4, person.getId());
			preparedStatement.execute();
			System.out.println("--- UPDATED 1 PERSON ---");
		} catch (SQLException e) {e.printStackTrace();}
	}

	public void delete(Integer id) {
		try{
			String query = "DELETE FROM Persons Where id="+id;
			Statement statement = connection.createStatement();
			statement.execute(query);
			System.out.println("--- PERSON REMOVED ---");
		}catch (SQLException e) {e.printStackTrace();}
	}


	public ResultSet selectAll() {
		ResultSet resultSet=null;
		try{
			String selectAll = "SELECT p.id, TRIM(p.name), TRIM(d.name), p.USD FROM persons p, directions d where p.way=d.id order by p.id";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(selectAll);
		} catch (SQLException e) {e.printStackTrace();}
		return resultSet;
	}

	public ResultSet selectWays() {
		ResultSet resultSet=null;
		try{
			String selectAll = "SELECT name FROM directions order by name";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(selectAll);
		} catch (SQLException e) {e.printStackTrace();}
		return resultSet;
	}

	public ResultSet selectId(Integer id) {
		ResultSet resultSet=null;
		try{
			String select = "SELECT p.id, TRIM(p.name), TRIM(d.name), p.usd FROM persons p, directions d WHERE p.way=d.id AND p.id = " + id;
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(select);
		} catch (SQLException e) {e.printStackTrace();}
		return resultSet;
	}

	public ResultSet findWay(String way) {
		ResultSet resultSet=null;
		try {
			String select = "SELECT id FROM directions WHERE name='" + way + "'";
			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(select);
		} catch (SQLException e) {e.printStackTrace();}
		return resultSet;
	}
}