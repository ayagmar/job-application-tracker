package com.ayagmar.jobapplicationtracker.jobposting.service;

import com.ayagmar.go.jobscraper.api.JobScraperApi;
import com.ayagmar.go.jobscraper.model.ApiSuccessResponse;
import com.ayagmar.go.jobscraper.model.ScraperJobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.model.ScraperConfig;
import com.ayagmar.jobapplicationtracker.jobposting.repository.JobPostingRepository;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import com.ayagmar.jobapplicationtracker.location.service.CityService;
import com.ayagmar.jobapplicationtracker.location.service.CompanyService;
import com.ayagmar.jobapplicationtracker.location.service.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class JobScraperService {
    private final JobScraperApi jobScraperApi;
    private final ScraperConfig scraperConfig;
    private final CompanyService companyService;
    private final JobPostingRepository jobPostingRepository;
    private final CityService cityService;

    public ApiSuccessResponse scrapeJobs(String jobTitle, String country, Integer pages, String source) {
        return jobScraperApi.scrapeJobs(jobTitle, country, pages, source);
    }

    public List<ScraperJobPosting> getJobs() {
        List<ScraperJobPosting> jobPosting = jobScraperApi.getJobs();
        log.info("retrieved {} jobListings from {}", jobPosting.size(), JobScraperApi.class.getSimpleName());
        return jobScraperApi.getJobs();
    }

    //@Scheduled(fixedDelayString = "${scraper.fixed-delay}")
    @Transactional
    public void scrapeAndProcessJobs() {
        log.info("Starting job scraping and processing");
        scraperConfig.getQueries().forEach(this::processQueryConfig);
        log.info("Completed job scraping and processing");
    }

    private void processQueryConfig(ScraperConfig.QueryConfig queryConfig) {
        log.info("Processing query: {}", queryConfig);
        try {
            //ApiSuccessResponse scrapeResponse = initiateScrapingJob(queryConfig);
            //log.info("Scraping initiated {}", scrapeResponse.getMessage());
            List<ScraperJobPosting> scrapedJobs = retrieveScrapedJobs();
            List<JobPosting> processedJobs = processJobPostings(scrapedJobs);
            saveJobPostings(processedJobs);
            log.info("Successfully processed {} job postings for query: {}", processedJobs.size(), queryConfig.getJobTitle());
        } catch (Exception e) {
            log.error("Error processing query: {}", queryConfig.getJobTitle(), e);
        }
    }

    private ApiSuccessResponse initiateScrapingJob(ScraperConfig.QueryConfig queryConfig) {
        log.debug("Initiating scrape job for query: {}", queryConfig.getJobTitle());
        ApiSuccessResponse response = jobScraperApi.scrapeJobs(
                queryConfig.getJobTitle(),
                queryConfig.getCountry(),
                queryConfig.getPages(),
                queryConfig.getSource()
        );
        log.info("Scrape job initiated: {}", response.getMessage());
        return response;
    }

    private List<ScraperJobPosting> retrieveScrapedJobs() {
        log.debug("Retrieving scraped jobs");
        List<ScraperJobPosting> scrapedJobs = jobScraperApi.getJobs();
        log.info("Retrieved {} scraped jobs", scrapedJobs.size());
        return scrapedJobs;
    }

    private List<JobPosting> processJobPostings(List<ScraperJobPosting> scrapedJobs) {
        log.debug("Processing {} scraped job postings", scrapedJobs.size());
        return scrapedJobs.stream()
                .map(this::processJobPosting)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }


    private Optional<JobPosting> processJobPosting(ScraperJobPosting scrapedJob) {
        try {
            log.debug("Processing job posting: {}", scrapedJob.getTitle());
            var company = companyService.findOrCreateCompany(CompanyMapper.INSTANCE.toCompanyRequest(scrapedJob));
            JobPosting jobPosting = createJobPosting(scrapedJob, company);
            log.debug("Successfully processed job posting: {}", scrapedJob.getTitle());
            return Optional.of(jobPosting);
        } catch (Exception e) {
            log.error("Error processing job posting: {}", scrapedJob.getTitle(), e);
            return Optional.empty();
        }
    }

    private JobPosting createJobPosting(ScraperJobPosting scrapedJob, CompanyResponse company) {
        return JobPosting.builder()
                .position(scrapedJob.getTitle())
                .url(scrapedJob.getCompanyDetails().getUrl())
                .description(scrapedJob.getDescription())
                .location(scrapedJob.getLocation())
                .summary(scrapedJob.getSummary())
                .city(cityService.findCityByLocationContaining(scrapedJob.getLocation()))
                .company(CompanyMapper.INSTANCE.toCompany(company))
                .build();
    }

    private void saveJobPostings(List<JobPosting> jobPostings) {
        log.debug("Saving {} job postings", jobPostings.size());
        jobPostingRepository.saveAll(jobPostings);
        log.info("Successfully saved {} job postings", jobPostings.size());
    }


}
