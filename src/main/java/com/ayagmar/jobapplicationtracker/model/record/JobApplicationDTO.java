package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDTO implements Serializable {
    private Long id;
    private Long userId;
    private Long jobPostingId;
    private LocalDateTime applicationDate;
    private LocalDate lastStatusChangeDate;
    private ApplicationStatus status;
    private String notes;
}