package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;

@Data
public class JobPostingRequest implements Serializable {
    private String position;
    private String url;
    private Long companyId;
    private Long cityId;
}