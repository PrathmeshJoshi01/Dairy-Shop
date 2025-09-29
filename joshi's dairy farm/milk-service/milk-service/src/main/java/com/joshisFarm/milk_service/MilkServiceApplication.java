package com.joshisFarm.milk_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.joshisFarm.milk_service.client") // 👈 important
public class MilkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilkServiceApplication.class, args);
	}

}
