package com.ayagmar.jobapplicationtracker.location.model.city;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityRequest implements Serializable {
    private String name;
    private String countryCode;
}
