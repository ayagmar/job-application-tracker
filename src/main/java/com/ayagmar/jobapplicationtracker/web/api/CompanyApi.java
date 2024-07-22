package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.CompanyRequest;
import com.ayagmar.jobapplicationtracker.model.record.CompanyResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/companies")
public interface CompanyApi {

    @PostMapping
    ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest);

    @GetMapping
    ResponseEntity<PaginatedResponse<CompanyResponse>> getAllCompanies(@ParameterObject Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCompany(@PathVariable Long id);
}
