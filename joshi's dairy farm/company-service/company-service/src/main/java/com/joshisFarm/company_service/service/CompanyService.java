package com.joshisFarm.company_service.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.joshisFarm.company_service.client.*;
import com.joshisFarm.company_service.dto.*;
import com.joshisFarm.company_service.entity.Company;
import com.joshisFarm.company_service.repository.CompanyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AuthClient authClient;
    private final FinanceClient financeClient;
    private final MilkClient milkClient;
    private final UserClient userClient;
    private final DistributorClient distributorClient;
    private final HealthClient healthClient;
    private final NotificationClient notificationClient;

    // Create company (owner must be set from auth)
    public CompanyDTO createCompany(CompanyDTO dto, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        Company company = Company.builder()
                .name(dto.getName())
                .owner(authUser.getUsername())
                .location(dto.getLocation())
                .productionRate(dto.getProductionRate())
                .earning(dto.getEarning())
                .workerCount(dto.getWorkerCount())
                .companyArea(dto.getCompanyArea())
                .build();

        company = companyRepository.save(company);
        return mapToDTO(company, authUser);
    }

    public CompanyDTO getCompany(Long id, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);
        Company company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        return mapToDTO(company, authUser);
    }

    public DashboardDTO getDashboard(Long companyId, String token) {
        UserResponseDTO authUser = authClient.validateToken(token);

        Company company = companyRepository.findById(companyId).orElse(null);
        CompanyDTO companyDTO = company == null ? null : mapToDTO(company, authUser);

        // Call other services for summaries - if any call fails, handle gracefully
        FinanceSummaryDTO finance = safeFinanceSummary(companyId, token);
        MilkSummaryDTO milk = safeMilkSummary(companyId, token);
        UserSummaryDTO users = safeUserSummary(companyId, token);
        DistributorSummaryDTO distributors = safeDistributorSummary(companyId, token);
        HealthSummaryDTO health = safeHealthSummary(companyId, token);

        DashboardDTO dashboard = DashboardDTO.builder()
                .company(companyDTO)
                .finance(finance)
                .milk(milk)
                .users(users)
                .distributors(distributors)
                .health(health)
                .build();

        // Basic alert example: if profit negative -> notify
        if (finance != null && finance.getProfit() != null && finance.getProfit() < 0) {
            notificationClient.sendNotification("Warning: Company " + (companyDTO != null ? companyDTO.getName() : companyId) +
                    " is running at a loss: " + finance.getProfit());
        }

        return dashboard;
    }

    // Helper safe callers: if remote call fails, return a default summary to avoid total failure
    private FinanceSummaryDTO safeFinanceSummary(Long companyId, String token) {
        try {
            return financeClient.getFinanceSummary(companyId, token);
        } catch (Exception e) {
            return FinanceSummaryDTO.builder().totalIncome(0.0).totalExpense(0.0).profit(0.0).build();
        }
    }

    private MilkSummaryDTO safeMilkSummary(Long companyId, String token) {
        try {
            return milkClient.getMilkSummary(companyId, token);
        } catch (Exception e) {
            return MilkSummaryDTO.builder().totalTodayLiters(0.0).averagePerCow(0.0).build();
        }
    }

    private UserSummaryDTO safeUserSummary(Long companyId, String token) {
        try {
            return userClient.getUserSummary(companyId, token);
        } catch (Exception e) {
            return UserSummaryDTO.builder().totalWorkers(0).totalVets(0).build();
        }
    }

    private DistributorSummaryDTO safeDistributorSummary(Long companyId, String token) {
        try {
            return distributorClient.getDistributorSummary(companyId, token);
        } catch (Exception e) {
            return DistributorSummaryDTO.builder().activeDistributors(0).pendingPayments(0.0).build();
        }
    }

    private HealthSummaryDTO safeHealthSummary(Long companyId, String token) {
        try {
            return healthClient.getHealthSummary(companyId, token);
        } catch (Exception e) {
            return HealthSummaryDTO.builder().totalAnimals(0).healthyCount(0).sickCount(0).build();
        }
    }

    private CompanyDTO mapToDTO(Company company, UserResponseDTO authUser) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .owner(company.getOwner())
                .location(company.getLocation())
                .productionRate(company.getProductionRate())
                // earnings visible only to owner
                .earning("OWNER".equalsIgnoreCase(authUser.getRole()) ? company.getEarning() : null)
                .workerCount(company.getWorkerCount())
                .companyArea(company.getCompanyArea())
                .build();
    }
}
