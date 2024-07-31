package com.ayagmar.jobapplicationtracker.location.model.country;

import com.ayagmar.jobapplicationtracker.location.model.city.CityResponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CountryResponse implements Serializable {
    private Long id;
    private String name;
    private String code;
    @JsonManagedReference
    private List<CityResponse> cities;
}