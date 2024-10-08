package com.ayagmar.jobapplicationtracker.jobapplication.rest.api;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.jobapplication.domain.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/applications")
public interface JobApplicationApi {
    @PostMapping
    ResponseEntity<JobApplicationResponse> createJobApplication(@RequestBody JobApplicationRequest jobApplicationRequest);

    @GetMapping("/{id}")
    ResponseEntity<JobApplicationResponse> getJobAppById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<PaginatedResponse<JobApplicationResponse>> getAllJobApps(@ParameterObject Pageable pageable);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJobApplication(@PathVariable Long id);

    @PutMapping("/{id}/status")
    ResponseEntity<JobApplicationResponse> updateStatus(@PathVariable Long id, @RequestBody ApplicationStatus status);

    @GetMapping("/status/{status}")
    ResponseEntity<PaginatedResponse<JobApplicationResponse>> getApplicationsByStatus(@ParameterObject Pageable pageable,
                                                                                      @PathVariable ApplicationStatus status);
}
