package com.ayagmar.jobapplicationtracker.model.record.jobposting;

import com.ayagmar.jobapplicationtracker.model.enums.EmploymentStatus;
import com.ayagmar.jobapplicationtracker.model.enums.WorkplaceType;
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