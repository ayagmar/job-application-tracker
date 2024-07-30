package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobscraper.api.JobsApi;
import com.ayagmar.jobscraper.model.JobListingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class JobScraperService {
    private final JobsApi jobsApi;

    public List<JobListingResponse> scrapeJobs(String jobTitle, String country, Integer pages, String source) {
        return jobsApi.scrapeJobs(jobTitle, country, pages, source);
    }

    public List<JobListingResponse> getJobs() {
        return jobsApi.getJobs();
    }

    @Scheduled(cron = "0 0 */6 * * ?") // Every 6 hours
    public void scheduledScrapeJobs() {
        try {
            log.info("Starting scheduled job scrape...");
            // Example parameters for scraping
            String jobTitle = "Java Developer";
            String country = "fr";
            Integer pages = 1;
            String source = "Indeed";

            List<JobListingResponse> jobListings = scrapeJobs(jobTitle, country, pages, source);
            log.info("Job scraping completed. Found {} job listings.", jobListings.size());
        } catch (Exception e) {
            log.error("Error occurred during scheduled job scraping.", e);
        }
    }

}
