package com.netcracker.edu.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table ( name = "PE" )
public class PeDao {

	@Id
	private String ip;
	private String type;
	private Integer temperature;
	private Integer speed;
	private Integer maxSpeed;
	private boolean fanActive;
	private Integer coordinateX;
	private Integer coordinateY;
}
