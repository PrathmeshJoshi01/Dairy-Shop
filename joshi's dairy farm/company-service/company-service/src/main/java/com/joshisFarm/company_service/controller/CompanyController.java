package com.joshisFarm.company_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joshisFarm.company_service.dto.CompanyDTO;
import com.joshisFarm.company_service.service.CompanyService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyDTO> createCompany(
            @RequestBody CompanyDTO dto,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(companyService.createCompany(dto, token));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }
}
