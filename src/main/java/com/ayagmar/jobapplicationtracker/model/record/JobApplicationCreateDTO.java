package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationCreateDTO implements Serializable {
    private Long userId;
    private Long jobPostingId;
    private LocalDateTime applicationDate;
    private ApplicationStatus status;
    private String notes;
}