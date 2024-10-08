package com.ayagmar.jobapplicationtracker.jobapplication.rest;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.jobapplication.domain.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationResponse;
import com.ayagmar.jobapplicationtracker.jobapplication.rest.api.JobApplicationApi;
import com.ayagmar.jobapplicationtracker.jobapplication.service.JobApplicationService;
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
public class JobApplicationResource implements JobApplicationApi {
    private final JobApplicationService jobApplicationService;

    @Override
    public ResponseEntity<JobApplicationResponse> createJobApplication(@RequestBody JobApplicationRequest jobApplicationRequest) {
        JobApplicationResponse jobApplicationResponse = jobApplicationService
                .createJobApp(jobApplicationRequest);
        return new ResponseEntity<>(jobApplicationResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<JobApplicationResponse> getJobAppById(@PathVariable Long id) {
        JobApplicationResponse jobApplicationResponse = jobApplicationService.getJobApplicationById(id);
        return new ResponseEntity<>(jobApplicationResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PaginatedResponse<JobApplicationResponse>> getAllJobApps(@ParameterObject Pageable pageable) {
        PaginatedResponse<JobApplicationResponse> applicationsByPage = jobApplicationService.getAllJobApplicationsByPage(pageable);
        return new ResponseEntity<>(applicationsByPage, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        jobApplicationService.deleteJobApplicationById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<JobApplicationResponse> updateStatus(@PathVariable Long id, @RequestBody ApplicationStatus status) {
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(id, status));
    }

    @Override
    public ResponseEntity<PaginatedResponse<JobApplicationResponse>>
    getApplicationsByStatus(@ParameterObject Pageable pageable,
                            @PathVariable ApplicationStatus status) {
        PaginatedResponse<JobApplicationResponse> applicationsByStatus = jobApplicationService.getApplicationsByStatus(status, pageable);
        return ResponseEntity.ok(applicationsByStatus);
    }

}
