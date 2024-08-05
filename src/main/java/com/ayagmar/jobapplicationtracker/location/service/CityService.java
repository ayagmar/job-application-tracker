package com.ayagmar.jobapplicationtracker.location.service;

import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.common.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.common.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.common.exception.FieldAlreadyExists;
import com.ayagmar.jobapplicationtracker.location.domain.City;
import com.ayagmar.jobapplicationtracker.location.model.city.CityRequest;
import com.ayagmar.jobapplicationtracker.location.model.city.CityResponse;
import com.ayagmar.jobapplicationtracker.location.repository.CityRepository;
import com.ayagmar.jobapplicationtracker.location.repository.CountryRepository;
import com.ayagmar.jobapplicationtracker.location.service.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "cities")
public class CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper mapper;

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
    @Cacheable
    public PaginatedResponse<CityResponse> getAllCities(Pageable pageable) {
        Page<City> cities = cityRepository.findAll(pageable);
        log.info("Retrieved {} cities", cities.getTotalElements());
        Page<CityResponse> cityResponsePage = cities.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(cityResponsePage);
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    public CityResponse getCityById(Long id) {
        var city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("city " + id + " is not found"));
        return mapper.toDTO(city);
    }

    @Transactional
    @CacheEvict(key = "#id", value = "cities")
    public void deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            log.info("City with id {} is deleted", id);
        } else {
            log.error("City with id {} does not exist", id);
        }
    }

    @Transactional(readOnly = true)
    public City findCityByLocationContaining(String location) {
        return cityRepository.findByCityNameContainingIgnoreCase(location)
                .orElseThrow(() -> new EntityNotFoundException("City not found for location: " + location));
    }


    private void validateName(String name) {
        cityRepository.findCityByName(name).ifPresent(company -> {
            throw new FieldAlreadyExists("name already exists: " + name);
        });
    }
}
