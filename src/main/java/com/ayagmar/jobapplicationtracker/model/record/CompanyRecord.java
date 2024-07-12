package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.Company;

/**
 * DTO for {@link Company}
 */
public record CompanyRecord(Long id, String name, String industry, String website) {
}
