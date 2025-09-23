package com.joshisFarm.milk_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.milk_service.client.AnimalClient;
import com.joshisFarm.milk_service.client.AuthClient;
import com.joshisFarm.milk_service.dto.MilkDTO;
import com.joshisFarm.milk_service.dto.UserResponseDTO;
import com.joshisFarm.milk_service.entity.MilkLog;
import com.joshisFarm.milk_service.repository.MilkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MilkService {

    private final MilkRepository milkRepository;
    private final AuthClient authClient;
    private final AnimalClient animalClient;

    // Add daily milk log
    public MilkDTO addMilkLog(MilkDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!(authUser.getRole().equalsIgnoreCase("OWNER") || 
              authUser.getRole().equalsIgnoreCase("MANAGER") || 
              authUser.getRole().equalsIgnoreCase("WORKER"))) {
            throw new RuntimeException("Access denied: Only Owner, Manager, or Worker can record milk.");
        }

        if (!animalClient.existsById(dto.getAnimalId())) {
            throw new RuntimeException("Animal not found.");
        }

        MilkLog log = MilkLog.builder()
                .animalId(dto.getAnimalId())
                .companyId(dto.getCompanyId())
                .quantity(dto.getQuantity())
                .fatPercent(dto.getFatPercent())
                .snfPercent(dto.getSnfPercent())
                .shift(dto.getShift())
                .date(LocalDate.now())
                .recordedBy(authUser.getId())
                .build();

        log = milkRepository.save(log);
        return mapToDTO(log);
    }

    // Get logs by animal
    public List<MilkDTO> getLogsByAnimal(Long animalId, String token) {
        authClient.validateToken(token);
        return milkRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get daily report by company
    public List<MilkDTO> getDailyReport(Long companyId, String token) {
        authClient.validateToken(token);
        return milkRepository.findByCompanyIdAndDate(companyId, LocalDate.now())
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MilkDTO mapToDTO(MilkLog log) {
        return MilkDTO.builder()
                .id(log.getId())
                .animalId(log.getAnimalId())
                .companyId(log.getCompanyId())
                .quantity(log.getQuantity())
                .fatPercent(log.getFatPercent())
                .snfPercent(log.getSnfPercent())
                .shift(log.getShift())
                .date(log.getDate())
                .recordedBy(log.getRecordedBy())
                .build();
    }
}