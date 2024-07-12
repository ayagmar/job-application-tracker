package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.JobPosting;

/**
 * DTO for {@link JobPosting}
 */
public record JobPostingRecord(
        Long id,
        String position,
        String url,
        Long companyId,
        String companyName,
        Long cityId,
        String cityName
) {
}
