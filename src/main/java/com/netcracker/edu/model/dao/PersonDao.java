package com.netcracker.edu.model.dao;

import javax.persistence.*;

@Entity
@Table (name = "PERSONS")
public class PersonDao {


	@Id
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "WAY", nullable = false)
	private WayDao way;

	@Column(name = "USD")
	private Integer USD;

	public PersonDao ( ) {
	}

	public Integer getId ( ) {
		return id;
	}

	public void setId (Integer id) {
		this.id = id;
	}

	public String getName ( ) {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public WayDao getWay ( ) {
		return way;
	}

	public void setWay (WayDao way) {
		this.way = way;
	}

	public Integer getUSD ( ) {
		return USD;
	}

	public void setUSD (Integer USD) {
		this.USD = USD;
	}
}