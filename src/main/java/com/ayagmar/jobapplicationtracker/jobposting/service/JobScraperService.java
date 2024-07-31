package com.ayagmar.jobapplicationtracker.jobposting.service;

import com.ayagmar.jobscraper.api.JobsApi;
import com.ayagmar.jobscraper.model.JobListingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobScraperService {
    private final JobsApi jobsApi;
    private final JobLauncher jobLauncher;
    private final Job scraperJob;
    private final Job getJobListingsJob;

    public List<JobListingResponse> scrapeJobs(String jobTitle, String country, Integer pages, String source) {
        return jobsApi.scrapeJobs(jobTitle, country, pages, source);
    }

    public List<JobListingResponse> getJobs() {
        return jobsApi.getJobs();
    }

    @Scheduled(fixedDelayString = "${scraper.fixedDelay}")
    public void scheduledScraping() {
        runScraperJob();
        runGetJobListingsJob();
    }

    public void runScraperJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(scraperJob, jobParameters);
        } catch (Exception e) {
            log.error("Error during job execution", e);
        }
    }

    public void runGetJobListingsJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(getJobListingsJob, jobParameters);
        } catch (Exception e) {
            log.error("Error during job execution", e);
        }
    }


}
