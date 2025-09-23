package com.joshisFarm.animal_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String breed;
	private Double weight;
	private Double height;
	private Double dailyMilkProduction;
	private Integer age;

	private String healthStatus;  
	private String cycleStatus;   
	
	@Column(nullable = false)
	private Long companyId;
}
