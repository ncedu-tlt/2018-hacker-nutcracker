package com.netcracker.edu;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "person")
@XmlType(propOrder = {"USD", "id", "name", "way"})

public class Person {
	private int USD;
	private int id;
	private String name;
	private String way;

	public Person() {
	}

	public Person(int id, String name, String way, int USD) {
		this.id = id;
		this.name = name;
		this.way = way;
		this.USD = USD;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getUSD() { return USD; }
	public void setUSD(int USD) { this.USD = USD; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getWay() { return way; }
	public void setWay(String way) { this.way = way; }

	@Override
	public String toString(){ return ""+ id + ", " + name + ", " + way + ", " + USD; }
}
