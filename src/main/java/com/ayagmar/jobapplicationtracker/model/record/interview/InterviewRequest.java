package com.ayagmar.jobapplicationtracker.model.record.interview;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class InterviewRequest implements Serializable {
    private Long jobApplicationId;
    private LocalDateTime interviewDate;
}