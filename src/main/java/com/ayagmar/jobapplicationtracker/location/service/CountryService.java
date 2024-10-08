package com.ayagmar.jobapplicationtracker.location.service;


import com.ayagmar.jobapplicationtracker.common.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.common.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.location.domain.Country;
import com.ayagmar.jobapplicationtracker.location.model.country.SimpleCountryResponse;
import com.ayagmar.jobapplicationtracker.location.repository.CountryRepository;
import com.ayagmar.jobapplicationtracker.location.service.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
@CacheConfig(cacheNames = "countries")
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper mapper;

    @Transactional(readOnly = true)
    @Cacheable
    public PaginatedResponse<SimpleCountryResponse> getAllCountries(Pageable pageable) {
        Page<Country> countries = countryRepository.findAll(pageable);
        log.info("Retrieved {} countries", countries.getTotalElements());
        Page<SimpleCountryResponse> countryResponses = countries.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(countryResponses);
    }

}
