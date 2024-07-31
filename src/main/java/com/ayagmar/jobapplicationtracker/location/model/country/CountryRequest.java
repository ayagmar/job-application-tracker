package com.ayagmar.jobapplicationtracker.location.model.country;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest implements Serializable {
    private String name;
    private String code;
}
