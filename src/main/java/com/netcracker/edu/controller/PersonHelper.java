package com.netcracker.edu.controller;
import com.netcracker.edu.Person;

import java.util.ArrayList;

public interface PersonHelper {

	void createPerson(String path, String nameFile, Person person, int choice);
	void deletePerson(int persId);
	void changePerson(Person person);
	ArrayList getAllPerson();
	Person getPerson(int persId);
	void parseFilesInDir(String path);
	void parseFile(String path, String nameFile);
}