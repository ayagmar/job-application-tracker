package com.ayagmar.jobapplicationtracker.jobposting.model;

import com.ayagmar.jobapplicationtracker.jobposting.enums.EmploymentStatus;
import com.ayagmar.jobapplicationtracker.jobposting.enums.WorkplaceType;
import com.ayagmar.jobapplicationtracker.location.model.city.CityResponse;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class JobPostingResponse implements Serializable {
    private Long id;
    private String platformJobId;
    private String position;
    private String url;
    private EmploymentStatus employmentStatus;
    private String location;
    private WorkplaceType workplaceType;
    private CompanyResponse company;
    private CityResponse city;
}