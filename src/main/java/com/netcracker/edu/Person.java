package com.netcracker.edu;

public class Person {
	private String id;
	private String usd;
	private String name;
	private String way;

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getUsd() { return usd; }
	public void setUsd(String usd) { this.usd = usd; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getWay() { return way; }
	public void setWay(String way) { this.way = way; }

	@Override
	public String toString() {
		return id + ", " + usd + ", " + name + ", " + way;
	}
}
