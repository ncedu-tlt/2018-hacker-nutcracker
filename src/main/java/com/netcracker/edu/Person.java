package com.netcracker.edu;

public class Person {
	private int id;
	private int usd;
	private String name;
	private String way;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public int getUsd() { return usd; }
	public void setUsd(int usd) { this.usd = usd; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getWay() { return way; }
	public void setWay(String way) { this.way = way; }

	@Override
	public String toString() {
		return "" + id + ", " + usd + ", " + name + ", " + way;
	}
}