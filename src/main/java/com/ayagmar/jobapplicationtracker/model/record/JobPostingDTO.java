package com.ayagmar.jobapplicationtracker.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingDTO implements Serializable {
    private Long id;
    private String position;
    private String url;
    private Long companyId;
    private String companyName;
    private Long cityId;
    private String cityName;
}