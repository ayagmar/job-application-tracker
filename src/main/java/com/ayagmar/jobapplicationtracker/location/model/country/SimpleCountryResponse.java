package com.ayagmar.jobapplicationtracker.location.model.country;

import lombok.Data;

import java.io.Serializable;

@Data
public class SimpleCountryResponse implements Serializable {
    private Long id;
    private String code;
    private String name;
}
