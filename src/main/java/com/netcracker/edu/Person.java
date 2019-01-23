package com.netcracker.edu;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "person")
@XmlType(propOrder = {"USD", "id", "name", "way"})
@JsonPropertyOrder({"id", "name", "way", "usd"})

public class Person {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("way")
	private String way;
	@JsonProperty("usd")
	private int USD;

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