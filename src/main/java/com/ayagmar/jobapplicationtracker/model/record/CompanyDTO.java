package com.ayagmar.jobapplicationtracker.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO implements Serializable {
    private Long id;
    private String name;
    private String industry;
    private String website;
    private Set<JobPostingDTO> jobPostings;
}
