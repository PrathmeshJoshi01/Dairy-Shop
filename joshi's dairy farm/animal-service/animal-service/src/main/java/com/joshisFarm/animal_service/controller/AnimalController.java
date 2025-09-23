package com.joshisFarm.animal_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joshisFarm.animal_service.dto.AnimalDTO;
import com.joshisFarm.animal_service.service.AnimalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDTO> addAnimal(@RequestBody AnimalDTO dto,
                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(animalService.addAnimal(dto, token));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<AnimalDTO>> getAnimalsByCompany(@PathVariable Long companyId,
                                                               @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(animalService.getAnimalsByCompany(companyId, token));
    }

    @PutMapping("/{id}/health")
    public ResponseEntity<AnimalDTO> updateHealth(@PathVariable Long id,
                                                  @RequestParam String healthStatus,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(animalService.updateHealth(id, healthStatus, token));
    }
}
