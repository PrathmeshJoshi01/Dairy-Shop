package com.joshisfarm.distributor_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.joshisfarm.distributor_service.dto.DeliveryDTO;
import com.joshisfarm.distributor_service.dto.DistributorDTO;
import com.joshisfarm.distributor_service.service.DistributorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/distributors")
@RequiredArgsConstructor
public class DistributorController {

    private final DistributorService distributorService;

    @PostMapping
    public ResponseEntity<DistributorDTO> addDistributor(@RequestBody DistributorDTO dto,
                                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(distributorService.addDistributor(dto, token));
    }

    @PostMapping("/delivery")
    public ResponseEntity<DeliveryDTO> recordDelivery(@RequestBody DeliveryDTO dto,
                                                      @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(distributorService.recordDelivery(dto, token));
    }

    @PostMapping("/delivery/{id}/pay")
    public ResponseEntity<String> markPayment(@PathVariable Long id,
                                              @RequestHeader("Authorization") String token) {
        distributorService.markPayment(id, token);
        return ResponseEntity.ok("Payment marked as received");
    }
}
