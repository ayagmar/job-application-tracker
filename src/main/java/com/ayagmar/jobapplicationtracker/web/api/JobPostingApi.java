package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobPostingResponse;
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

@RequestMapping("/api/jobs")
public interface JobPostingApi {
    @PostMapping
    ResponseEntity<JobPostingResponse> createJobPosting(@RequestBody JobPostingRequest jobPostingRequest);

    @GetMapping
    ResponseEntity<PaginatedResponse<JobPostingResponse>> getAllJobPostings(@ParameterObject Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<JobPostingResponse> getJobPostingById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJobPosting(@PathVariable Long id);
}

