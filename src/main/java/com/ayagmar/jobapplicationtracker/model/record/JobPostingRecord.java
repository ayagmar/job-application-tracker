package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.JobPosting;

import java.io.Serializable;

/**
 * DTO for {@link JobPosting}
 */
public record JobPostingRecord(Long id, String position, String url) implements Serializable {
    public JobPosting toJobPosting() {
        return JobPosting.builder()
                .id(id)
                .position(position)
                .url(url)
                .build();
    }
}