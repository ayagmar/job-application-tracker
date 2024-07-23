package com.ayagmar.jobapplicationtracker.model.record.company;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyResponse implements Serializable {
    private Long id;
    private String name;
    private String industry;
    private String website;
}
