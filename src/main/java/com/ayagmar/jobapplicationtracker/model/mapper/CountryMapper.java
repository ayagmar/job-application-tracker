package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.Country;
import com.ayagmar.jobapplicationtracker.model.record.country.SimpleCountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    SimpleCountryResponse toDTO(Country country);
}
