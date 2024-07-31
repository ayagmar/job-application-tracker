package com.ayagmar.jobapplicationtracker.jobposting.batch;

import com.ayagmar.jobscraper.api.JobsApi;
import com.ayagmar.jobscraper.model.JobListingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JobListingProcessor implements ItemProcessor<ScraperConfig.QueryConfig, List<JobListingResponse>> {
    private final JobsApi jobsApi;

    @Override
    public List<JobListingResponse> process(ScraperConfig.QueryConfig queryConfig) {
        log.info("Processing query: {}", queryConfig);
        try {
            return jobsApi.scrapeJobs(
                    queryConfig.getJobTitle(),
                    queryConfig.getCountry(),
                    queryConfig.getPages(),
                    queryConfig.getSource()
            );
        } catch (Exception e) {
            log.error("Error processing query: {}", queryConfig, e);
            return null;
        }
    }
}