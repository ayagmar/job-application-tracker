package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobPostingResponse implements Serializable {
    private Long id;
    private String position;
    private String url;
    private CompanyResponse company;
    private CityResponse city;
}