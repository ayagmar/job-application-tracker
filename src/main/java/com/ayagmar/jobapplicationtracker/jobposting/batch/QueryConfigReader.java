package com.ayagmar.jobapplicationtracker.jobposting.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;

@RequiredArgsConstructor
public class QueryConfigReader implements ItemReader<ScraperConfig.QueryConfig> {
    private final ScraperConfig scraperConfig;
    private int index = 0;

    @Override
    public ScraperConfig.QueryConfig read() {
        if (index < scraperConfig.getQueries().size()) {
            return scraperConfig.getQueries().get(index++);
        }
        return null;
    }
}