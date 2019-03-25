package com.netcracker.edu.model.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DIRECTIONS")
public class WayDao {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "SEQ_FOR_NUTCRACKER")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@OneToMany (mappedBy = "way")
	private List<PersonDao> persons;

	public WayDao ( ) {
	}

	public WayDao (Integer id, String name, List<PersonDao> persons) {
		this.id = id;
		this.name = name;
		this.persons = persons;
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

	public List<PersonDao> getPersons ( ) {
		return persons;
	}

	public void setPersons (List<PersonDao> persons) {
		this.persons = persons;
	}
}
