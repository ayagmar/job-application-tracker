package com.ayagmar.jobapplicationtracker.jobposting.rest;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingResponse;
import com.ayagmar.jobapplicationtracker.jobposting.rest.api.JobPostingApi;
import com.ayagmar.jobapplicationtracker.jobposting.service.JobPostingService;
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

public class JobPostingResource implements JobPostingApi {
    private final JobPostingService jobPostingService;

    @Override
    public ResponseEntity<JobPostingResponse> createJobPosting(@RequestBody JobPostingRequest jobPostingRequest) {
        JobPostingResponse jobPostingResponse = jobPostingService.createJobPosting(jobPostingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobPostingResponse);
    }

    @Override
    public ResponseEntity<PaginatedResponse<JobPostingResponse>> getAllJobPostings(@ParameterObject Pageable pageable) {
        PaginatedResponse<JobPostingResponse> jobPostingPage = jobPostingService.getAllJobPostingsByPage(pageable);
        return ResponseEntity.ok(jobPostingPage);
    }


    @Override
    public ResponseEntity<JobPostingResponse> getJobPostingById(@PathVariable Long id) {
        JobPostingResponse jobPostingResponse = jobPostingService.getJobPostingById(id);
        return ResponseEntity.ok(jobPostingResponse);
    }


    @Override
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPostingById(id);
        return ResponseEntity.noContent().build();
    }
}
