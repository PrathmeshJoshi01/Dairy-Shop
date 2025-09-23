package com.joshisFarm.breeding_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joshisFarm.breeding_service.dto.BreedingRecordDTO;
import com.joshisFarm.breeding_service.service.BreedingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/breeding")
@RequiredArgsConstructor
public class BreedingController {

    private final BreedingService breedingService;

    @PostMapping
    public ResponseEntity<BreedingRecordDTO> addRecord(@RequestBody BreedingRecordDTO dto,
                                                       @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(breedingService.addRecord(dto, token));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<BreedingRecordDTO>> getRecords(@PathVariable Long animalId,
                                                              @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(breedingService.getRecords(animalId, token));
    }
}