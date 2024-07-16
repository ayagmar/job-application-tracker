package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class CompanyResponse implements Serializable {
    private Long id;
    private String name;
    private String industry;
    private String website;
    private Set<JobPostingResponse> jobPostings;
}
