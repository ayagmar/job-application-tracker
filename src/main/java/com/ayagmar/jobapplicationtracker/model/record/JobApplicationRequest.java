package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobApplicationRequest implements Serializable {
    private Long userId;
    private Long jobPostingId;
    private Long documentId;
    private String notes;
}