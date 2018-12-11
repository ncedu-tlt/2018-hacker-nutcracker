package com.netcracker.edu;

import java.util.HashMap;

public class Model {

	private HashMap<String, Person> map = new HashMap<>();

	public HashMap<String, Person> getMap (){ return map; }
	public void setMap(HashMap<String, Person> map){ this.map = map; }

}