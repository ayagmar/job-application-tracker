package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityResponse implements Serializable {
    private Long id;
    private String name;
    private Long countryId;
}
