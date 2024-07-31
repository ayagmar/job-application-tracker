package com.ayagmar.jobapplicationtracker.jobposting.batch;

import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
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
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JobScraperBatchConfig {

    private static final String JOB_NAME = "scraperJob";
    private static final String STEP_NAME = "scraperStep";
    private final JobsApi jobsApi;
    private final ScraperConfig scraperConfig;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job scraperJob(Step scraperStep) {
        return new JobBuilder(JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(scraperStep)
                .end()
                .build();
    }

    @Bean
    public Step scraperStep() {
        return new StepBuilder(STEP_NAME, jobRepository)
                .<ScraperConfig.QueryConfig, List<JobPosting>>chunk(1, transactionManager)
                .reader(queryConfigReader())
                .processor(compositeProcessor())
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
        return new JobPostingProcessor();
    }

    @Bean
    public ItemProcessor<ScraperConfig.QueryConfig, List<JobPosting>> compositeProcessor() {
        return new CompositeItemProcessorBuilder<ScraperConfig.QueryConfig, List<JobPosting>>()
                .delegates(jobListingProcessor(), jobPostingProcessor())
                .build();
    }

    @Bean
    public ItemWriter<List<JobPosting>> jobPostingWriter() {
        return new JobPostingWriter();
    }
}