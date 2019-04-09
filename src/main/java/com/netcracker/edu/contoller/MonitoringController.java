package com.netcracker.edu.contoller;

import com.netcracker.edu.entity.dto.CpeDto;
import com.netcracker.edu.entity.dto.PeDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping ( "/service" )
public class MonitoringController {

	@PostMapping ( "/test" )
	public void test ( ) {
		System.out.println("Test success!");
	}

	@PostMapping ( "/internet" )
	public void changeInternetOnCpe (@RequestBody CpeDto cpe) {
		RestTemplate rt = new RestTemplate();
		String uri = "http://localhost:8080/cpe/internet";
		rt.postForEntity(uri, cpe, CpeDto.class);
	}

	@PostMapping ( "/fan" )
	public void changeFanOnPe (@RequestBody PeDto pe) {
		RestTemplate rt = new RestTemplate();
		String uri = "???????????????????????????????????";//address pe
		rt.postForEntity(uri, pe, PeDto.class);
	}
}
