package com.ayagmar.jobapplicationtracker.model.record;

import java.time.LocalDateTime;

public record InterviewRecord(Long id, Long jobApplicationId, LocalDateTime interviewDate) {
}
