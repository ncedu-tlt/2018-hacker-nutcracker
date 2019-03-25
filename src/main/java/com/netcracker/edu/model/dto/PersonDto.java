package com.netcracker.edu.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement (name = "person")
@XmlType (propOrder = {"USD", "id", "name", "way"})
@JsonPropertyOrder ({"id", "name", "way", "USD"})
public class PersonDto {

	@JsonProperty ("id")
	private Integer id;
	@JsonProperty ("name")
	private String name;
	@JsonProperty ("way")
	private String way;
	@JsonProperty ("USD")
	private Integer USD;

	public PersonDto ( ) {
	}

	public PersonDto (Integer id, String name, String way, Integer USD) {
		this.id = id;
		this.name = name;
		this.way = way;
		this.USD = USD;
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

	public String getWay ( ) {
		return way;
	}

	public void setWay (String way) {
		this.way = way;
	}

	public Integer getUSD ( ) {
		return USD;
	}

	public void setUSD (Integer USD) {
		this.USD = USD;
	}
}
