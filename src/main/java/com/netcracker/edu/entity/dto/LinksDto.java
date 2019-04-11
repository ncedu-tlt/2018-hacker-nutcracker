package com.netcracker.edu.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksDto {

	private HashMap<Integer, String> cpeLinks;
	private HashMap<Integer, String> peLinks;
}
