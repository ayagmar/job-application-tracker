package com.ayagmar.jobapplicationtracker.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
}