package com.joshisFarm.animal_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.animal_service.client.AuthClient;
import com.joshisFarm.animal_service.dto.AnimalDTO;
import com.joshisFarm.animal_service.dto.UserResponseDTO;
import com.joshisFarm.animal_service.entity.Animal;
import com.joshisFarm.animal_service.repository.AnimalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AuthClient authClient;

    // Add animal
    public AnimalDTO addAnimal(AnimalDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!(authUser.getRole().equalsIgnoreCase("OWNER") || authUser.getRole().equalsIgnoreCase("MANAGER") || authUser.getRole().equalsIgnoreCase("VET"))) {
            throw new RuntimeException("Access denied: Only Owner, Manager, or Vet can add animals.");
        }

        Animal animal = Animal.builder()
                .name(dto.getName())
                .breed(dto.getBreed())
                .weight(dto.getWeight())
                .height(dto.getHeight())
                .dailyMilkProduction(dto.getDailyMilkProduction())
                .age(dto.getAge())
                .healthStatus(dto.getHealthStatus())
                .cycleStatus(dto.getCycleStatus())
                .companyId(dto.getCompanyId())
                .build();

        animal = animalRepository.save(animal);
        return mapToDTO(animal);
    }

    // Get animals for a company
    public List<AnimalDTO> getAnimalsByCompany(Long companyId, String token) {
        authClient.validateToken(token); // just validate
        return animalRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Update health status
    public AnimalDTO updateHealth(Long animalId, String healthStatus, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        if (!(authUser.getRole().equalsIgnoreCase("OWNER") || authUser.getRole().equalsIgnoreCase("VET"))) {
            throw new RuntimeException("Access denied: Only Owner or Vet can update health.");
        }

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));

        animal.setHealthStatus(healthStatus);
        animal = animalRepository.save(animal);

        return mapToDTO(animal);
    }

    // Helper method
    private AnimalDTO mapToDTO(Animal animal) {
        return AnimalDTO.builder()
                .id(animal.getId())
                .name(animal.getName())
                .breed(animal.getBreed())
                .weight(animal.getWeight())
                .height(animal.getHeight())
                .dailyMilkProduction(animal.getDailyMilkProduction())
                .age(animal.getAge())
                .healthStatus(animal.getHealthStatus())
                .cycleStatus(animal.getCycleStatus())
                .companyId(animal.getCompanyId())
                .build();
    }
}