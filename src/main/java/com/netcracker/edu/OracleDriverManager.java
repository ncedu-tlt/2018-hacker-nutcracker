package com.netcracker.edu;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDriverManager {

//	private String login;
//	private String password;

	public Connection openOracleConnection() {
		try {
			Driver driver = createOracleDriver();
			DriverManager.registerDriver(driver);

			Connection connection = getConnection();
			return connection;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	public void getLogPas(String login, String password){
//		this.login=login;
//		this.password=password;
//	}

	private Driver createOracleDriver() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return (Driver) Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	}

	private Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe", "TLT_27", "TLT_27");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}