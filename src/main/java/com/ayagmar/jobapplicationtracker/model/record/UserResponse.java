package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Set<JobApplicationResponse> jobApplications;
}
