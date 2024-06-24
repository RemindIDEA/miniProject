package com.mini.zomboid;

import com.mini.zomboid.Service.MapServiceImpl;
import com.mini.zomboid.controller.MapController;
import com.mini.zomboid.domain.BoundingBoxDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@ActiveProfiles("local")
@SpringBootTest
class TestApplicationTests {

	@Autowired
	MapServiceImpl mapService;


	@Test
	void timeStampEx() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
		String formatTime = now.format(formatter);
		System.out.println("formatTime = " + formatTime);
	}
	@Value("${base-path}") String a;
	@Value("${backup-path}") String b;

	
	@Test
	void ymlTest(){

		System.out.println("a = " + a);
		System.out.println("b = " + b);
//		//@Value("${basePath}")
//		String test;
//		System.out.println("test = " + test);

	}


	@Test
	void testMapInit(){
		BoundingBoxDTO boundingBoxDTO = new BoundingBoxDTO();
		System.out.println("boundingBoxDTO.getXmin() = " + boundingBoxDTO.getXmin());
		System.out.println("boundingBoxDTO.getXmax() = " + boundingBoxDTO.getXmax());
		System.out.println("boundingBoxDTO.getYmin() = " + boundingBoxDTO.getYmin());
		System.out.println("boundingBoxDTO.get = " + boundingBoxDTO.getYmax());

		mapService.moveBackup(boundingBoxDTO);

		
		
	}




}
