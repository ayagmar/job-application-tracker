package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.model.DocumentType;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationResponse;
import com.ayagmar.jobapplicationtracker.repository.DocumentRepository;
import com.ayagmar.jobapplicationtracker.repository.JobApplicationRepository;
import com.ayagmar.jobapplicationtracker.repository.JobPostingRepository;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final EntityMapper mapper;

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
        jobApplication.setLastStatusChangeDate(LocalDate.from(LocalDateTime.now()));

        var savedJobApplication = jobApplicationRepository.save(jobApplication);

        return mapper.toDTO(savedJobApplication);
    }

}
