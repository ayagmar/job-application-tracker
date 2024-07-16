package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityRequest implements Serializable {
    private String name;
    private Long countryId;
}
