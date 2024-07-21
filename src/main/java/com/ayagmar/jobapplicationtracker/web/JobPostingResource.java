package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.model.record.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobPostingResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.service.JobPostingService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobPostingResource {
    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<JobPostingResponse> createJobPosting(@RequestBody JobPostingRequest jobPostingRequest) {
        JobPostingResponse jobPostingResponse = jobPostingService.createJobPosting(jobPostingRequest);
        return new ResponseEntity<>(jobPostingResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<JobPostingResponse>> getAllJobPostings(@ParameterObject Pageable pageable) {

        PaginatedResponse<JobPostingResponse> jobPostingPage = jobPostingService.getAllJobPostingsByPage(pageable);

        return new ResponseEntity<>(jobPostingPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingResponse> getCompanyById(@PathVariable Long id) {
        JobPostingResponse jobPostingResponse = jobPostingService.getJobPostingById(id);
        return new ResponseEntity<>(jobPostingResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPostingById(id);
        return ResponseEntity.noContent().build();
    }
}
