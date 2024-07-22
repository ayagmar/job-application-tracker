package com.ayagmar.jobapplicationtracker.model.record;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.io.Serializable;

@Data
public class CityResponse implements Serializable {
    private Long id;
    private String name;
    @JsonBackReference
    private SimpleCountryResponse country;
//    private Set<JobPostingResponse> jobPostings;
}
