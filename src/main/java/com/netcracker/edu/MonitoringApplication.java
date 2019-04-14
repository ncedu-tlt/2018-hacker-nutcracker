package com.netcracker.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class MonitoringApplication {
	public static void main (String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(MonitoringApplication.class);
	}
}
