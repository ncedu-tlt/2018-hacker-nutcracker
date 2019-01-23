package com.netcracker.edu;

import java.sql.*;

public final class WorkWithOracle {

	private static Connection connection;
	private static volatile WorkWithOracle instance;

	private WorkWithOracle(Connection connection) {
		this.connection = connection;
	}

	public static WorkWithOracle getInstance(Connection connection) {
		if (instance == null) {
			synchronized (WorkWithOracle.class) {
				if (instance == null) {
					instance = new WorkWithOracle(connection);
				}
			}
		}
		return instance;
	}

	public void insertPersonWay(Person person) throws SQLException {
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
	}

	public void insertPerson(Person person, Integer wayId) throws SQLException {
		String queryForPerson = "INSERT INTO Persons (id, name, way, USD) VALUES (?, ?, ?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(queryForPerson);
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2, person.getName());
		preparedStatement.setInt(3, wayId);
		preparedStatement.setInt(4, person.getUSD());
		preparedStatement.execute();
		System.out.println("--- INSERTED 1 PERSON ---");
	}

	public void updatePersonWay(Person person) throws SQLException {
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
	}

	public void updatePerson(Person person, Integer wayId) throws SQLException {
		String queryForPerson = "UPDATE Persons SET name = ?, way = ?, USD = ? WHERE id = ?";

		PreparedStatement preparedStatement = connection.prepareStatement(queryForPerson);
		preparedStatement.setString(1, person.getName());
		preparedStatement.setInt(2, wayId);
		preparedStatement.setInt(3, person.getUSD());
		preparedStatement.setInt(4, person.getId());
		preparedStatement.execute();
		System.out.println("--- UPDATED 1 PERSON ---");
	}

	public void delete(Integer id) throws SQLException {
		String query = "DELETE FROM Persons Where id="+id;
		Statement statement = connection.createStatement();
		statement.execute(query);
		System.out.println("--- PERSON REMOVED ---");
	}


	public ResultSet selectAll() throws SQLException {
		String selectAll = "SELECT p.id, p.name, d.name, p.USD FROM persons p, directions d where p.way=d.id";
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery(selectAll);
		return resultSet;
	}

	public ResultSet selectId(Integer id) throws SQLException {//Выполняется для вывода персона на экран и проверки
															   //существования айди в таблице (надо делать отдельный
															   //запрос для проверки, так как тут слияние 2х табл происходит?)
		String select = "SELECT p.id, p.name, d.name, p.usd FROM persons p, directions d WHERE p.way=d.id AND p.id = " + id;
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery(select);
		return resultSet;
	}

	public ResultSet findWay(String way) throws SQLException {
		String select = "SELECT id FROM directions WHERE name='" + way + "'";
		Statement stmt = connection.createStatement();
		ResultSet resultSet = stmt.executeQuery(select);
		return resultSet;
	}
}