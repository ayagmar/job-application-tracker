package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data

public class InterviewResponse implements Serializable {
    private Long id;
    private LocalDateTime interviewDate;
    private JobApplicationResponse jobApplication;
}