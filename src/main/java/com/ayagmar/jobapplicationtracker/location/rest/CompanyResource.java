package com.ayagmar.jobapplicationtracker.location.rest;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import com.ayagmar.jobapplicationtracker.location.rest.api.CompanyApi;
import com.ayagmar.jobapplicationtracker.location.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CompanyResource implements CompanyApi {
    private final CompanyService companyService;

    @Override
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse createCompany = companyService.createCompany(companyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCompany);
    }


    @Override
    public ResponseEntity<PaginatedResponse<CompanyResponse>> getAllCompanies(@ParameterObject Pageable pageable) {
        PaginatedResponse<CompanyResponse> companyPage = companyService.getAllCompaniesByPage(pageable);
        return ResponseEntity.ok(companyPage);
    }


    @Override
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        CompanyResponse companyResponse = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyResponse);
    }


    @Override
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

}
