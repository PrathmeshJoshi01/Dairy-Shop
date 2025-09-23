package com.joshisFarm.company_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.joshisFarm.company_service.client.AuthClient;
import com.joshisFarm.company_service.dto.CompanyDTO;
import com.joshisFarm.company_service.dto.UserResponseDTO;
import com.joshisFarm.company_service.entity.Company;
import com.joshisFarm.company_service.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {
	private final CompanyRepository companyRepository;
    private final AuthClient authClient;

    public CompanyDTO createCompany(CompanyDTO dto, String token) {
        // 1. Validate JWT with Auth Service
        UserResponseDTO user = authClient.validateToken(token);

        // 2. Build entity
        Company company = Company.builder()
                .name(dto.getName())
                .owner(user.getUsername()) // owner comes from Auth
                .location(dto.getLocation())
                .productionRate(dto.getProductionRate())
                .earning(dto.getEarning())
                .workerCount(dto.getWorkerCount())
                .companyArea(dto.getCompanyArea())
                .build();

        // 3. Save
        company = companyRepository.save(company);

        return mapToDTO(company);
    }

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private CompanyDTO mapToDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .owner(company.getOwner())
                .location(company.getLocation())
                .productionRate(company.getProductionRate())
                .earning(company.getEarning())
                .workerCount(company.getWorkerCount())
                .companyArea(company.getCompanyArea())
                .build();
    }
}
