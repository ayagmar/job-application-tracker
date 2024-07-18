package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.model.record.CompanyRequest;
import com.ayagmar.jobapplicationtracker.model.record.CompanyResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ayagmar.jobapplicationtracker.web.PaginationDefaults.DEFAULT_PAGE_SIZE;
import static com.ayagmar.jobapplicationtracker.web.PaginationDefaults.DEFAULT_PAGE_VALUE;
import static com.ayagmar.jobapplicationtracker.web.PaginationDefaults.DEFAULT_SORT_ATTRIBUTE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
public class CompanyResource {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        CompanyResponse createCompany = companyService.createCompany(companyRequest);
        return new ResponseEntity<>(createCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CompanyResponse>> getAllCities(
            @RequestParam(defaultValue = DEFAULT_PAGE_VALUE) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size,
            @RequestParam(defaultValue = DEFAULT_SORT_ATTRIBUTE) String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        PaginatedResponse<CompanyResponse> companyPage = companyService.getAllCompaniesByPage(pageable);

        return new ResponseEntity<>(companyPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        CompanyResponse userResponse = companyService.getCompanyById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletecompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().body("company " + id + " deleted successfully");
    }

}
