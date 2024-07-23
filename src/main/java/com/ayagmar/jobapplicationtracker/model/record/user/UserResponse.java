package com.ayagmar.jobapplicationtracker.model.record.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
}
