package com.ayagmar.jobapplicationtracker.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewDTO implements Serializable {
    private Long id;
    private Long jobApplicationId;
    private LocalDateTime interviewDate;
}