package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class JobApplicationResponse implements Serializable {
    private Long id;
    private LocalDateTime applicationDate;
    private LocalDate lastStatusChangeDate;
    private ApplicationStatus status;
    private String notes;
    private UserResponse user;
    private JobPostingResponse jobPosting;
}