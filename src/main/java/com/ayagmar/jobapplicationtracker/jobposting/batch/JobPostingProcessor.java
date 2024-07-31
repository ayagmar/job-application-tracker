package com.ayagmar.jobapplicationtracker.jobposting.batch;


import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.location.repository.CityRepository;
import com.ayagmar.jobapplicationtracker.location.service.CompanyService;
import com.ayagmar.jobapplicationtracker.location.service.mapper.CompanyMapper;
import com.ayagmar.jobscraper.model.JobListingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class JobPostingProcessor implements ItemProcessor<List<JobListingResponse>, List<JobPosting>> {
    private final CompanyService companyService;
    private final CityRepository cityRepository;

    @Override
    @Transactional
    public List<JobPosting> process(List<JobListingResponse> jobListings) {
        return jobListings.stream()
                .map(this::transformToJobPosting)
                .collect(Collectors.toList());
    }


    private JobPosting transformToJobPosting(JobListingResponse jobListing) {
        log.info("Transforming JobListing {} to Job posting", jobListing.getTitle().get());
        var company = companyService.findOrCreateCompany(CompanyMapper.INSTANCE.toCompanyRequest(jobListing));
        return JobPosting.builder()
                .position(jobListing.getTitle().orElse(StringUtils.EMPTY))
                .location(jobListing.getLocation().orElse(StringUtils.EMPTY))
                .summary(jobListing.getSummary().orElse(StringUtils.EMPTY))
                .description(jobListing.getFullJobDescription().orElse(StringUtils.EMPTY))
                .url(jobListing.getUrl().get())
                .company(CompanyMapper.INSTANCE.toCompany(company))
                .city(cityRepository.findCityByName("Basel").get())//temp city, to be extracted from location
                .build();
    }
}