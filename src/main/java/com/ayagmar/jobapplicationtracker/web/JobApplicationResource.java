package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class JobApplicationResource {
    private final JobApplicationService jobApplicationService;

    @PostMapping
    public ResponseEntity<JobApplicationResponse> createApplication(@RequestBody JobApplicationRequest jobApplicationRequest) {
        JobApplicationResponse jobApplicationResponse = jobApplicationService
                .createJobApp(jobApplicationRequest);
        return new ResponseEntity<>(jobApplicationResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getJobAppById(@PathVariable Long id) {
        JobApplicationResponse jobApplicationResponse = jobApplicationService.getJobApplicationById(id);
        return new ResponseEntity<>(jobApplicationResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<JobApplicationResponse>> getAllJobApps(@ParameterObject Pageable pageable) {

        PaginatedResponse<JobApplicationResponse> applicationsByPage = jobApplicationService.getAllJobApplicationsByPage(pageable);

        return new ResponseEntity<>(applicationsByPage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        jobApplicationService.deleteJobApplicationById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplicationResponse> updateStatus(@PathVariable Long id,
                                                               @RequestBody ApplicationStatus status) {
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(id, status));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<PaginatedResponse<JobApplicationResponse>>
    getApplicationsByStatus(@ParameterObject Pageable pageable,
                            @PathVariable ApplicationStatus status) {
        PaginatedResponse<JobApplicationResponse> applicationsByStatus = jobApplicationService.getApplicationsByStatus(status, pageable);
        return ResponseEntity.ok(applicationsByStatus);
    }

}
