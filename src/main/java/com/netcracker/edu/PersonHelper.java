package com.netcracker.edu;

import java.io.IOException;
import java.sql.SQLException;

public interface PersonHelper {

	boolean createPerson(String path, String nameFile, Person person, Integer choice) throws SQLException, IOException;
	void deletePerson(Integer persId) throws SQLException;
	void changePerson(Person person) throws SQLException;
	String getAllPerson() throws SQLException;
	String getPerson(Integer id) throws SQLException;
	void parseFilesInDir(String path) throws SQLException;
	boolean parseFile(String path, String nameFile) throws SQLException;
}