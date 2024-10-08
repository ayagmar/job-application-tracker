package com.ayagmar.jobapplicationtracker.jobapplication.service;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.common.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.common.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.document.domain.DocumentType;
import com.ayagmar.jobapplicationtracker.document.repository.DocumentRepository;
import com.ayagmar.jobapplicationtracker.jobapplication.domain.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.jobapplication.domain.JobApplication;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationResponse;
import com.ayagmar.jobapplicationtracker.jobapplication.repository.JobApplicationRepository;
import com.ayagmar.jobapplicationtracker.jobapplication.service.mapper.JobApplicationMapper;
import com.ayagmar.jobapplicationtracker.jobposting.repository.JobPostingRepository;
import com.ayagmar.jobapplicationtracker.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final JobApplicationRepository jobApplicationRepository;
    private final JobApplicationMapper mapper;

    public JobApplicationResponse createJobApp(JobApplicationRequest jobApplicationRequest) {
        var jobApplication = mapper.toEntity(jobApplicationRequest);
        var user = userRepository.findById(jobApplicationRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        var jobPosting = jobPostingRepository.findById(jobApplicationRequest.getJobPostingId())
                .orElseThrow(() -> new EntityNotFoundException("Job Posting not found"));
        var document = documentRepository.findById(jobApplicationRequest.getDocumentId())
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        jobApplication.setUser(user);
        jobApplication.setJobPosting(jobPosting);

        jobApplication.addDocument(DocumentType.RESUME, document.getId());
        jobApplication.setApplicationDate(LocalDateTime.now());
        jobApplication.setStatus(ApplicationStatus.APPLIED);
        jobApplication.setLastStatusChangeDate(LocalDate.now());

        var savedJobApplication = jobApplicationRepository.save(jobApplication);

        return mapper.toDTO(savedJobApplication);
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<JobApplicationResponse> getAllJobApplicationsByPage(Pageable pageable) {
        Page<JobApplication> jobApplications = jobApplicationRepository.findAll(pageable);
        log.info("Retrieved {} jobApplications", jobApplications.getTotalElements());
        Page<JobApplicationResponse> jobApplicationResponsePage = jobApplications.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(jobApplicationResponsePage);
    }

    @Transactional(readOnly = true)
    public JobApplicationResponse getJobApplicationById(Long id) {
        var jobApplication = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application " + id + " is not found"));
        return mapper.toDTO(jobApplication);
    }

    @Transactional
    public void deleteJobApplicationById(Long id) {
        this.getJobApplicationById(id);
        jobApplicationRepository.deleteById(id);
        log.info("Job posting with id " + id + " is deleted");
    }


    public JobApplicationResponse updateApplicationStatus(Long id, ApplicationStatus newStatus) {
        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application not found"));
        application.setStatus(newStatus);
        application.setLastStatusChangeDate(LocalDate.now());
        var updatedJobApplication = jobApplicationRepository.save(application);
        return mapper.toDTO(updatedJobApplication);
    }

    public PaginatedResponse<JobApplicationResponse> getApplicationsByStatus(ApplicationStatus status, Pageable page) {
        Page<JobApplication> jobApplications = jobApplicationRepository.findByStatus(status, page);
        Page<JobApplicationResponse> jobApplicationResponsePage = jobApplications.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(jobApplicationResponsePage);
    }

}
