package com.ayagmar.jobapplicationtracker.jobposting.rest;

import com.ayagmar.go.jobscraper.model.ApiSuccessResponse;
import com.ayagmar.go.jobscraper.model.ScraperJobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.service.JobScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/external")
//temporary class, this will be called automatically without directly exposing
public class JobScraperController {
    private final JobScraperService jobScraperService;

    @PostMapping("/scrape")
    public ApiSuccessResponse scrapeJobs(
            @RequestParam String jobTitle,
            @RequestParam(required = false, defaultValue = "ch") String country,
            @RequestParam(required = false, defaultValue = "1") Integer pages,
            @RequestParam(required = false, defaultValue = "indeed") String source) {
        return jobScraperService.scrapeJobs(jobTitle, country, pages, source);
    }

    @GetMapping("/jobs")
    public List<ScraperJobPosting> getJobs() {
        return jobScraperService.getJobs();
    }

}
