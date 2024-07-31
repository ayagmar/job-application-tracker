package com.ayagmar.jobapplicationtracker.jobposting.batch;

import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.repository.JobPostingRepository;
import com.ayagmar.jobapplicationtracker.location.repository.CityRepository;
import com.ayagmar.jobapplicationtracker.location.service.CompanyService;
import com.ayagmar.jobscraper.api.JobsApi;
import com.ayagmar.jobscraper.model.JobListingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobScraperBatchConfig {

    private static final String SCRAPER_JOB_NAME = "scraperJob";
    private static final String SCRAPER_JOB_STEP = "scraperStep";

    private static final String GET_JOB_LISTINGS_JOB_NAME = "getJobListingsJob";
    private static final String GET_JOB_LISTINGS_STEPS = "getJobsStep";
    private final JobsApi jobsApi;
    private final ScraperConfig scraperConfig;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CompanyService companyService;
    private final JobPostingRepository jobPostingRepository;
    private final CityRepository cityRepository;

    @Bean
    public Job scraperJob(Step scraperStep) {
        return new JobBuilder(SCRAPER_JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(scraperStep)
                .end()
                .build();
    }

    @Bean
    public Step scraperStep() {
        return new StepBuilder(SCRAPER_JOB_STEP, jobRepository)
                .<ScraperConfig.QueryConfig, List<JobPosting>>chunk(1, transactionManager)
                .reader(queryConfigReader())
                .processor(compositeProcessor())
                .writer(jobPostingWriter())
                .build();
    }

    @Bean
    public Job getJobListingsJob(Step getJobListingsStep) {
        return new JobBuilder(GET_JOB_LISTINGS_JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(getJobListingsStep)
                .end()
                .build();
    }

    @Bean
    public Step getJobListingsStep() {
        return new StepBuilder(GET_JOB_LISTINGS_STEPS, jobRepository)
                .<List<JobListingResponse>, List<JobPosting>>chunk(1, transactionManager)
                .reader(getJobsReader())
                .processor(jobPostingProcessor())
                .writer(jobPostingWriter())
                .build();
    }

    @Bean
    public ItemReader<ScraperConfig.QueryConfig> queryConfigReader() {
        return new QueryConfigReader(scraperConfig);
    }

    @Bean
    public ItemProcessor<ScraperConfig.QueryConfig, List<JobListingResponse>> jobListingProcessor() {
        return new JobListingProcessor(jobsApi);
    }

    @Bean
    public ItemProcessor<List<JobListingResponse>, List<JobPosting>> jobPostingProcessor() {
        return new JobPostingProcessor(companyService, cityRepository);
    }

    @Bean
    public ItemProcessor<ScraperConfig.QueryConfig, List<JobPosting>> compositeProcessor() {
        return new CompositeItemProcessorBuilder<ScraperConfig.QueryConfig, List<JobPosting>>()
                .delegates(jobListingProcessor(), jobPostingProcessor())
                .build();
    }

    @Bean
    public ItemWriter<List<JobPosting>> jobPostingWriter() {
        return new JobPostingWriter(jobPostingRepository);
    }


    @Bean
    public ItemReader<List<JobListingResponse>> getJobsReader() {
        List<JobListingResponse> jobs = jobsApi.getJobs();
        log.info("Retrieved {} jobs", jobs.size());
        return new ListItemReader<>(List.of(jobs));
    }

}