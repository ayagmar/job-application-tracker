package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.model.JobPosting;
import com.ayagmar.jobapplicationtracker.model.record.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobPostingResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.repository.CityRepository;
import com.ayagmar.jobapplicationtracker.repository.CompanyRepository;
import com.ayagmar.jobapplicationtracker.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class JobPostingService {
    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;
    private final CityRepository cityRepository;
    private final EntityMapper entityMapper;

    @Transactional
    public JobPostingResponse createJobPosting(JobPostingRequest jobPostingRequest) {
        var jobPosting = entityMapper.toEntity(jobPostingRequest);
        var city = cityRepository.findById(jobPostingRequest.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City " + jobPostingRequest.getCityId() + " is not found"));
        var company = companyRepository.findById(jobPostingRequest.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company " + jobPostingRequest.getCompanyId() + " is not found"));
        jobPosting.setCity(city);
        jobPosting.setCompany(company);
        var savedJobPosting = jobPostingRepository.save(jobPosting);
        return entityMapper.toDTO(savedJobPosting);
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<JobPostingResponse> getAllJobPostingsByPage(Pageable pageable) {
        Page<JobPosting> jobPostings = jobPostingRepository.findAll(pageable);
        log.info("Retrieved {} jobPostings", jobPostings.getTotalElements());
        Page<JobPostingResponse> jobPostingsPage = jobPostings.map(entityMapper::toDTO);
        return PaginatedResponseFactory.createFrom(jobPostingsPage);
    }

    @Transactional(readOnly = true)
    public JobPostingResponse getJobPostingById(Long id) {
        var jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jobposting " + id + " is not found"));
        return entityMapper.toDTO(jobPosting);
    }

    @Transactional
    public void deleteJobPostingById(Long id) {
        getJobPostingById(id);
        jobPostingRepository.deleteById(id);
        log.info("Job posting with id " + id + " is deleted");
    }


}
