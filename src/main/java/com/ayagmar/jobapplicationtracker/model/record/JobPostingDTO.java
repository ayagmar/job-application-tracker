package com.ayagmar.jobapplicationtracker.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDTO {
    private Long id;
    private String position;
    private String url;
    private CompanyDTO company;
    private CityDTO city;
}