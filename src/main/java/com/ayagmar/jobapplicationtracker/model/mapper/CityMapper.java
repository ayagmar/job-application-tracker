package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.City;
import com.ayagmar.jobapplicationtracker.model.record.city.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.city.CityResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City toEntity(CityRequest city);

    CityResponse toDTO(City city);
}
