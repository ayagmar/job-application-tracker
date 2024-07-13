package com.ayagmar.jobapplicationtracker.model.record;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryWithCitiesDTO implements Serializable {
    private Long id;
    private String name;
    private String code;
    private List<String> cities;
}