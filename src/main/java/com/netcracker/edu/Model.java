package com.netcracker.edu;

import java.util.HashMap;

public class Model {

	private static HashMap<String, Person> map = new HashMap<>();

	public static HashMap<String, Person> getMap (){ return map; }
	public void setMap(HashMap<String, Person> map){ this.map = map; }

}