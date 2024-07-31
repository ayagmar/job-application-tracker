package com.ayagmar.jobapplicationtracker.jobposting.service;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.common.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.common.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingResponse;
import com.ayagmar.jobapplicationtracker.jobposting.repository.JobPostingRepository;
import com.ayagmar.jobapplicationtracker.jobposting.service.mapper.JobPostingMapper;
import com.ayagmar.jobapplicationtracker.location.repository.CityRepository;
import com.ayagmar.jobapplicationtracker.location.repository.CompanyRepository;
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
    private final JobPostingMapper entityMapper;

    @Transactional
    public JobPostingResponse createJobPosting(JobPostingRequest jobPostingRequest) {
        var jobPosting = entityMapper.toEntity(jobPostingRequest);
        var city = cityRepository.findById(jobPostingRequest.getCityId())
                .orElseThrow(() -> new EntityNotFoundException("City " + jobPostingRequest.getCityId() + " is not found"));
        var company = companyRepository.findById(jobPostingRequest.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company " + jobPostingRequest.getCompanyId() + " is not found"));
        jobPosting.setCity(city);
        jobPosting.setCompany(company);
        jobPosting.setLocation(String.join(", ", city.getName(), city.getCountry().getCode()));
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
