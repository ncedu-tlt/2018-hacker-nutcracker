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
@Table ( name = "CPE" )
public class CpeDao {

	@Id
	private String ip;
	private String type;
	private boolean internetActive;
	private Integer maxDownlinkSpeed;
	private Integer downlinkSpeed;
	private String peIpAddress;
	private Integer coordinateX;
	private Integer coordinateY;
}
