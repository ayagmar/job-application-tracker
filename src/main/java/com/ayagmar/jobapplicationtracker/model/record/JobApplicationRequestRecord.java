package com.ayagmar.jobapplicationtracker.model.record;

public record JobApplicationRequestRecord(Long userId, String companyName, JobPostingRecord jobPostingRecord, String notes) {

}
