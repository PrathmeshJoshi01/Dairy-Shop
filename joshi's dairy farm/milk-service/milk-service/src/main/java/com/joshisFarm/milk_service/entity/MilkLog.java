package com.joshisFarm.milk_service.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "milk_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MilkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long animalId;     // reference to animal
    private Long companyId;    // reference to company

    private Double quantity;   // in liters
    private Double fatPercent;
    private Double snfPercent;
    private String shift;      // Morning / Evening
    private LocalDate date;

    private Long recordedBy;   // userId of recorder
}