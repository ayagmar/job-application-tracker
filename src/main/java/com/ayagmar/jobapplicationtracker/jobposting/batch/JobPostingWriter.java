package com.ayagmar.jobapplicationtracker.jobposting.batch;


import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JobPostingWriter implements ItemWriter<List<JobPosting>> {
    private final JobPostingRepository jobPostingRepository;

    @Override
    public void write(Chunk<? extends List<JobPosting>> chunks) {
        for (List<JobPosting> jobList : chunks) {
            for (JobPosting job : jobList) {
                jobPostingRepository.save(job);
                log.info("Processed and saved job posting: Position={}, Company={}, Location={}, URL={}",
                        job.getPosition(), job.getCompany(), job.getLocation(), job.getUrl());
            }
        }
    }
}