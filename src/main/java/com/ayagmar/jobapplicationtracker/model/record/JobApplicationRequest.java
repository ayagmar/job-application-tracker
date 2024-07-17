package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class JobApplicationRequest implements Serializable {
    private Long userId;
    private Long jobPostingId;
    private LocalDateTime applicationDate;
    private Long documentId;
    private ApplicationStatus status;
    private String notes;
}