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
	private Integer maxDownlinkSpeed;
	private Integer downlinkSpeed;
	private boolean isFanActive;
	private Integer coordinateX;
	private Integer coordinateY;
}
