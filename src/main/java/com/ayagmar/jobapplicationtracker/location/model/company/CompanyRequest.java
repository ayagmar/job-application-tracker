package com.ayagmar.jobapplicationtracker.location.model.company;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyRequest implements Serializable {
    private String name;
    private String industry;
    private String website;
}