package com.netcracker.edu.controller;
import com.netcracker.edu.Person;

import java.util.ArrayList;

public interface PersonHelper {

	void createPerson(Person person);
	void deletePerson(int persId);
	void changePerson(Person person);
	ArrayList getAllPerson();
	Person getPerson(Integer id);
}