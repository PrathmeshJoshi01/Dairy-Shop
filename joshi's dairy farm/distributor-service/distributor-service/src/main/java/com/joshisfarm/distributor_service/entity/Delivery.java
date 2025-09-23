package com.joshisfarm.distributor_service.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deliveries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long distributorId;
	private Double quantityLiters;
	private LocalDate deliveryDate;
	private String shift; // Morning/Evening
	private Double price; // amount due for this delivery
	private boolean paid;
}
