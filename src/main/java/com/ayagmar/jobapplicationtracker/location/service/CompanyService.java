package com.ayagmar.jobapplicationtracker.location.service;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.common.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.common.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.common.exception.FieldAlreadyExists;
import com.ayagmar.jobapplicationtracker.location.domain.Company;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import com.ayagmar.jobapplicationtracker.location.repository.CompanyRepository;
import com.ayagmar.jobapplicationtracker.location.service.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;

    @Transactional
    public CompanyResponse createCompany(CompanyRequest companyRequest) {
        validateName(companyRequest.getName());
        var company = mapper.toEntity(companyRequest);
        company = companyRepository.save(company);
        log.info("Company created with name: {}", company.getName());
        return mapper.toDTO(company);
    }

    @Transactional(readOnly = true)
    public CompanyResponse getCompanyById(Long id) {
        var company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company " + id + " is not found"));
        return mapper.toDTO(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        getCompanyById(id);
        companyRepository.deleteById(id);
        log.info("Company with id " + id + " is deleted");
    }

    private void validateName(String name) {
        companyRepository.findByName(name).ifPresent(company -> {
            throw new FieldAlreadyExists("name already exists: " + name);
        });
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<CompanyResponse> getAllCompaniesByPage(Pageable pageable) {
        Page<Company> companies = companyRepository.findAll(pageable);
        log.info("Retrieved {} companies", companies.getTotalElements());

        Page<CompanyResponse> companyResponsePage = companies.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(companyResponsePage);
    }

}


