package com.ayagmar.jobapplicationtracker.location.service.mapper;

import com.ayagmar.jobapplicationtracker.location.domain.Country;
import com.ayagmar.jobapplicationtracker.location.model.country.SimpleCountryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    SimpleCountryResponse toDTO(Country country);
}
