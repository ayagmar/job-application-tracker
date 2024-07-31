package com.ayagmar.jobapplicationtracker.jobposting.model;

import com.ayagmar.jobapplicationtracker.jobposting.enums.EmploymentStatus;
import com.ayagmar.jobapplicationtracker.jobposting.enums.WorkplaceType;
import lombok.Data;

import java.io.Serializable;

@Data
public class JobPostingRequest implements Serializable {
    private String position;
    private String url;
    private String description;
    private WorkplaceType workplaceType;
    private EmploymentStatus employmentStatus;
    private Long companyId;
    private Long cityId;

}