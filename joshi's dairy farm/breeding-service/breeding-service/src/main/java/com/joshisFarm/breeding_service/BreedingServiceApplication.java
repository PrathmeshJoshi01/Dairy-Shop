package com.joshisFarm.breeding_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.joshisFarm.breeding_service.client")
public class BreedingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BreedingServiceApplication.class, args);
	}

}
