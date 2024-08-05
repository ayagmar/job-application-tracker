package com.ayagmar.jobapplicationtracker.jobposting.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "scraper")
@Data
public class ScraperConfig {
    private List<QueryConfig> queries;
    private String fixedDelay;

    @Data
    public static class QueryConfig {
        private String jobTitle;
        private String country;
        private Integer pages;
        private String source;
    }
}