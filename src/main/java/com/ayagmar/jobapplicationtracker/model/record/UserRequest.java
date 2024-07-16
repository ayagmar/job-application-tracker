package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String username;
    private String firstname;
    private String lastname;
}