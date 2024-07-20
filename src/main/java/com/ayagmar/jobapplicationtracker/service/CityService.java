package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.exception.FieldAlreadyExists;
import com.ayagmar.jobapplicationtracker.model.City;
import com.ayagmar.jobapplicationtracker.model.record.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.CityResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.repository.CityRepository;
import com.ayagmar.jobapplicationtracker.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final EntityMapper mapper;

    @Transactional
    public CityResponse createCity(CityRequest cityRequest) {
        validateName(cityRequest.getName());
        var city = mapper.toEntity(cityRequest);
        var country = countryRepository.findByCode(cityRequest.getCountryCode()).
                orElseThrow(() -> new EntityNotFoundException("Country does not exist"));
        city.setCountry(country);
        var savedCity = cityRepository.save(city);
        log.info("City created with name: {}", savedCity.getName());
        return mapper.toDTO(savedCity);
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<CityResponse> getAllCities(Pageable pageable) {
        Page<City> cities = cityRepository.findAll(pageable);
        log.info("Retrieved {} cities", cities.getTotalElements());
        Page<CityResponse> cityResponsePage = cities.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(cityResponsePage);
    }

    @Transactional(readOnly = true)
    public CityResponse getCityById(Long id) {
        var city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("city " + id + " is not found"));
        return mapper.toDTO(city);
    }

    @Transactional
    public void deleteCity(Long id) {
        getCityById(id);
        cityRepository.deleteById(id);
        log.info("City with id " + id + " is deleted");
    }

    private void validateName(String name) {
        cityRepository.findCityByName(name).ifPresent(company -> {
            throw new FieldAlreadyExists("name already exists: " + name);
        });
    }
}
