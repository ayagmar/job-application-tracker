package com.ayagmar.jobapplicationtracker.location.rest.api;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Company", description = "the company API")
public interface CompanyApi {

    @Operation(summary = "Create company", description = "Creates a new company")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "City created successfully")})
    @PostMapping
    ResponseEntity<CompanyResponse> createCompany(@Parameter(description = "Created Company object") @Valid @RequestBody CompanyRequest companyRequest);

    @Operation(summary = "Fetch a paginated companies list from database", description = "Retrieves companies pageable from Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping
    ResponseEntity<PaginatedResponse<CompanyResponse>> getAllCompanies(@ParameterObject Pageable pageable);

    @Operation(summary = "Fetch a company by id", description = "Retrieves a company by Id from the db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/{id}")
    ResponseEntity<CompanyResponse> getCompanyById(@Parameter(description = "Existing User Id") @PathVariable Long id);

    @Operation(summary = "Delete a company by id", description = "Deletes a company by Id from the db")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "successful operation")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCompany(@Parameter(description = "Existing User Id") @PathVariable Long id);
}
