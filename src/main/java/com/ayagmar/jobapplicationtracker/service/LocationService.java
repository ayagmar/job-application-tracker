package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.entity.Country;
import com.ayagmar.jobapplicationtracker.entity.CountryRecord;
import com.ayagmar.jobapplicationtracker.entity.InitializationStatus;
import com.ayagmar.jobapplicationtracker.repository.CountryRepository;
import com.ayagmar.jobapplicationtracker.repository.InitializationStatusRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    public static final String FILE_PATH = "countries.json";
    private final CountryRepository countryRepository;
    private final InitializationStatusRepository initializationStatusRepository;

    @PostConstruct
    public void init() {
        if (!isCountriesLoaded()) {
            loadCountriesFromJson();
            setCountriesLoaded();
        }
    }
    private void loadCountriesFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = new ClassPathResource(FILE_PATH).getInputStream();
            List<CountryRecord> countryDTOs = objectMapper.readValue(inputStream, new TypeReference<>() {
            });

            for (CountryRecord countryDTO : countryDTOs) {
                if (!countryRepository.existsByCode(countryDTO.getCode())) {
                    Country country = new Country();
                    country.setName(countryDTO.getName());
                    country.setCode(countryDTO.getCode());
                    countryRepository.save(country);
                }
            }
        } catch (IOException e) {
            log.error("Something went wrong during countries load");
            throw new RuntimeException(e);
        }
    }
    private boolean isCountriesLoaded() {
        return initializationStatusRepository.findByName("countries").
                map(InitializationStatus::isLoaded).
                orElse(false);
    }

    private void setCountriesLoaded() {
        InitializationStatus status = InitializationStatus.builder()
                .name("countries")
                .isLoaded(Boolean.TRUE)
                .build();
        initializationStatusRepository.save(status);
    }



}
