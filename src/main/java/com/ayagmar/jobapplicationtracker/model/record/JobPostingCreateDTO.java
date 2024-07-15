package com.ayagmar.jobapplicationtracker.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostingCreateDTO implements Serializable {
    private String position;
    private String url;
    private Long companyId;
    private Long cityId;
}