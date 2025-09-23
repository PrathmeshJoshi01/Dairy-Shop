package com.joshisfarm.finance_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshisfarm.finance_service.dto.TransactionDTO;
import com.joshisfarm.finance_service.service.FinanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @PostMapping
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO dto,
                                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(financeService.addTransaction(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(financeService.getAllTransactions(token));
    }

    @GetMapping("/profit")
    public ResponseEntity<Double> getProfitOrLoss(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(financeService.getProfitOrLoss(token));
    }
}
