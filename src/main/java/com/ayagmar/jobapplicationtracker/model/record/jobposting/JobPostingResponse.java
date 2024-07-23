package com.ayagmar.jobapplicationtracker.model.record.jobposting;

import com.ayagmar.jobapplicationtracker.model.enums.EmploymentStatus;
import com.ayagmar.jobapplicationtracker.model.enums.WorkplaceType;
import com.ayagmar.jobapplicationtracker.model.record.city.CityResponse;
import com.ayagmar.jobapplicationtracker.model.record.company.CompanyResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class JobPostingResponse implements Serializable {
    private Long id;
    private String position;
    private String url;
    private EmploymentStatus employmentStatus;
    private String location;
    private WorkplaceType workplaceType;
    private CompanyResponse company;
    private CityResponse city;
}