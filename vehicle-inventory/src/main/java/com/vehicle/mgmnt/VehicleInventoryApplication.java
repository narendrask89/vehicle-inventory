package com.vehicle.mgmnt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
public class VehicleInventoryApplication {

	public static void main(String[] args) throws JsonProcessingException {
		
		//ObjectMapper mapper = new ObjectMapper();
		//System.out.println(mapper.writeValueAsString(new Vehicle()));
		SpringApplication.run(VehicleInventoryApplication.class, args);
	}
}
