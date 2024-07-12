package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.model.JobApplication;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link JobApplication}
 */
public record JobApplicationDto(Long id, UserRecord user, CompanyRecord company, JobPostingRecord jobPosting,
                                LocalDateTime applicationDate, LocalDate lastStatusChangeDate, ApplicationStatus status,
                                String notes) implements Serializable {
}