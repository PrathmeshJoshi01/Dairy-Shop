package com.joshisfarm.distributor_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.joshisfarm.distributor_service.client") 
public class DistributorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributorServiceApplication.class, args);
	}

}
