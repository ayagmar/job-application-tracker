package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;

import java.time.LocalDateTime;

public record JobApplicationRecord(
        Long id,
        Long userId,
        String userName,
        Long jobPostingId,
        String jobPosition,
        String companyName,
        String cityName,
        LocalDateTime applicationDate,
        ApplicationStatus status,
        String notes
) {
}
