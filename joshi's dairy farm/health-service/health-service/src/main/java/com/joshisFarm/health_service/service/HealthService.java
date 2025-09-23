package com.joshisFarm.health_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.health_service.client.AnimalClient;
import com.joshisFarm.health_service.client.AuthClient;
import com.joshisFarm.health_service.client.NotificationClient;
import com.joshisFarm.health_service.dto.HealthRecordDTO;
import com.joshisFarm.health_service.dto.UserResponseDTO;
import com.joshisFarm.health_service.entity.HealthRecord;
import com.joshisFarm.health_service.repository.HealthRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthService {

	private final HealthRecordRepository healthRecordRepository;
    private final AuthClient authClient;
    private final AnimalClient animalClient;
    private final NotificationClient notificationClient;

    // Add new health record
    public HealthRecordDTO addRecord(HealthRecordDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!(authUser.getRole().equalsIgnoreCase("OWNER") || 
              authUser.getRole().equalsIgnoreCase("VET"))) {
            throw new RuntimeException("Access denied: Only Owner or Vet can add health records.");
        }

        if (!animalClient.existsById(dto.getAnimalId())) {
            throw new RuntimeException("Animal not found.");
        }

        HealthRecord record = HealthRecord.builder()
                .animalId(dto.getAnimalId())
                .diagnosis(dto.getDiagnosis())
                .medication(dto.getMedication())
                .visitDate(dto.getVisitDate())
                .vetName(dto.getVetName())
                .nextVisit(dto.getNextVisit())
                .build();

        record = healthRecordRepository.save(record);

        // send reminder notification if next visit is scheduled
        if (record.getNextVisit() != null) {
            notificationClient.sendNotification("Next vet visit scheduled on " 
                + record.getNextVisit() + " for animal " + record.getAnimalId());
        }

        return mapToDTO(record);
    }

    // Get all records for an animal
    public List<HealthRecordDTO> getRecordsByAnimal(Long animalId, String token) {
        authClient.validateToken(token);
        return healthRecordRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private HealthRecordDTO mapToDTO(HealthRecord record) {
        return HealthRecordDTO.builder()
                .id(record.getId())
                .animalId(record.getAnimalId())
                .diagnosis(record.getDiagnosis())
                .medication(record.getMedication())
                .visitDate(record.getVisitDate())
                .vetName(record.getVetName())
                .nextVisit(record.getNextVisit())
                .build();
    }
}
