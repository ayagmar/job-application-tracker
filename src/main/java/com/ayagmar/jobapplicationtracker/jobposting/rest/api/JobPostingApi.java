package com.ayagmar.jobapplicationtracker.jobposting.rest.api;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingResponse;
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

@RequestMapping("/api/jobs")
@Tag(name = "JobPosting", description = "the Job posting API")
public interface JobPostingApi {
    @Operation(summary = "Create JobPosting", description = "Creates a new job posting")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "JobPosting created successfully")})
    @PostMapping
    ResponseEntity<JobPostingResponse> createJobPosting(@Parameter(description = "Created user object") @Valid @RequestBody JobPostingRequest jobPostingRequest);

    @Operation(summary = "Fetch a paginated list from database", description = "Retrieves users pageable from Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping
    ResponseEntity<PaginatedResponse<JobPostingResponse>> getAllJobPostings(@ParameterObject Pageable pageable);

    @Operation(summary = "Fetch a job posting by id", description = "Retrieves a jobPosting entity by Id from the Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/{id}")
    ResponseEntity<JobPostingResponse> getJobPostingById(@Parameter(description = "Existing jobPosting Id") @PathVariable Long id);

    @Operation(summary = "Delete job posting", description = "Deletes a job posting from the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "successful operation")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJobPosting(@Parameter(description = "Existing jobPosting Id") @PathVariable Long id);
}

