package com.ayagmar.jobapplicationtracker.jobapplication.model;

import com.ayagmar.jobapplicationtracker.jobapplication.domain.ApplicationStatus;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingResponse;
import com.ayagmar.jobapplicationtracker.user.model.UserResponse;
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