package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.Company;

import java.io.Serializable;

/**
 * DTO for {@link Company}
 */
public record CompanyRecord(Long id, String name, CountryRecord country,
                            String industry, String website) implements Serializable {
}