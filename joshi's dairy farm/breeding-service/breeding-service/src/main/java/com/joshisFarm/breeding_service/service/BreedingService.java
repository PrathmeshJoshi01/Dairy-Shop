package com.joshisFarm.breeding_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.breeding_service.client.AnimalClient;
import com.joshisFarm.breeding_service.client.AuthClient;
import com.joshisFarm.breeding_service.client.NotificationClient;
import com.joshisFarm.breeding_service.dto.BreedingRecordDTO;
import com.joshisFarm.breeding_service.dto.UserResponseDTO;
import com.joshisFarm.breeding_service.entity.BreedingRecord;
import com.joshisFarm.breeding_service.repository.BreedingRecordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BreedingService {

    private final BreedingRecordRepository breedingRecordRepository;
    private final AuthClient authClient;
    private final AnimalClient animalClient;
    private final NotificationClient notificationClient;

    public BreedingRecordDTO addRecord(BreedingRecordDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!(authUser.getRole().equalsIgnoreCase("OWNER") ||
              authUser.getRole().equalsIgnoreCase("VET") ||
              authUser.getRole().equalsIgnoreCase("MANAGER"))) {
            throw new RuntimeException("Access denied.");
        }

        if (!animalClient.existsById(dto.getAnimalId())) {
            throw new RuntimeException("Animal not found.");
        }

        BreedingRecord record = BreedingRecord.builder()
                .animalId(dto.getAnimalId())
                .eventType(dto.getEventType())
                .eventDate(dto.getEventDate())
                .notes(dto.getNotes())
                .build();

        record = breedingRecordRepository.save(record);

        // Notify on certain events
        if ("PregnancyCheck".equalsIgnoreCase(record.getEventType())) {
            notificationClient.sendNotification("Pregnancy check recorded for animal " + record.getAnimalId());
        } else if ("Calving".equalsIgnoreCase(record.getEventType())) {
            notificationClient.sendNotification("Calving recorded for animal " + record.getAnimalId());
        }

        return mapToDTO(record);
    }

    public List<BreedingRecordDTO> getRecords(Long animalId, String token) {
        authClient.validateToken(token);
        return breedingRecordRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BreedingRecordDTO mapToDTO(BreedingRecord record) {
        return BreedingRecordDTO.builder()
                .id(record.getId())
                .animalId(record.getAnimalId())
                .eventType(record.getEventType())
                .eventDate(record.getEventDate())
                .notes(record.getNotes())
                .build();
    }
}