package com.ayagmar.jobapplicationtracker.location.service.mapper;

import com.ayagmar.jobapplicationtracker.location.domain.City;
import com.ayagmar.jobapplicationtracker.location.model.city.CityRequest;
import com.ayagmar.jobapplicationtracker.location.model.city.CityResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toEntity(CityRequest city);

    CityResponse toDTO(City city);
}
