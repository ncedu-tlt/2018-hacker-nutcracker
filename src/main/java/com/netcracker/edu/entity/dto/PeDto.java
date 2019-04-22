package com.netcracker.edu.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeDto {

	private String ip;
	private String type;
	private Integer temperature;
	private Integer speed;
	private Integer maxSpeed;
	private boolean fanActive;
	private Integer coordinateX;
	private Integer coordinateY;
}
