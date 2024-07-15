package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDTO {
    private Long id;
    private LocalDateTime applicationDate;
    private LocalDate lastStatusChangeDate;
    private ApplicationStatus status;
    private String notes;
    private UserDTO user;
    private JobPostingDTO jobPosting;
}