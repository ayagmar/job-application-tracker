package com.ayagmar.jobapplicationtracker.jobposting.batch;


import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobscraper.model.JobListingResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;
import java.util.stream.Collectors;

import static com.ayagmar.jobapplicationtracker.jobposting.enums.EmploymentStatus.FULL_TIME;
import static com.ayagmar.jobapplicationtracker.jobposting.enums.WorkplaceType.HYBRID;

public class JobPostingProcessor implements ItemProcessor<List<JobListingResponse>, List<JobPosting>> {
    @Override
    public List<JobPosting> process(List<JobListingResponse> jobListings) {
        return jobListings.stream()
                .map(this::transformToJobPosting)
                .collect(Collectors.toList());
    }

    private JobPosting transformToJobPosting(JobListingResponse jobListing) {
        return JobPosting.builder()
                .position(jobListing.getTitle().orElse(StringUtils.EMPTY))
                .location(jobListing.getLocation().orElse(StringUtils.EMPTY))
                .summary(jobListing.getSummary().orElse(StringUtils.EMPTY))
                .description(jobListing.getFullJobDescription().orElse(StringUtils.EMPTY))
                .url(jobListing.getUrl().get())
                .company(null)
                .city(null)
                .employmentStatus(FULL_TIME)
                .workplaceType(HYBRID)
                .build();
    }
}