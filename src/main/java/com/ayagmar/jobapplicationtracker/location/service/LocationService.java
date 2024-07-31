package com.ayagmar.jobapplicationtracker.location.service;

import com.ayagmar.jobapplicationtracker.location.domain.City;
import com.ayagmar.jobapplicationtracker.location.domain.Country;
import com.ayagmar.jobapplicationtracker.location.domain.InitializationStatus;
import com.ayagmar.jobapplicationtracker.location.model.country.CountryWithCitiesDTO;
import com.ayagmar.jobapplicationtracker.location.repository.CountryRepository;
import com.ayagmar.jobapplicationtracker.location.repository.InitializationStatusRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    public static final String FILE_PATH = "countries.json";
    public static final String COUNTRIES = "countries";
    private final CountryRepository countryRepository;
    private final InitializationStatusRepository initializationStatusRepository;

    @PostConstruct
    public void init() {
        if (isFalse(isCountriesLoaded())) {
            loadCountriesFromJson();
        }
    }

    private void loadCountriesFromJson() {
        boolean isLoaded = FALSE;
        try (InputStream inputStream = new ClassPathResource(FILE_PATH).getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<CountryWithCitiesDTO> countryRecords = objectMapper.readValue(inputStream, new TypeReference<>() {
            });

            for (CountryWithCitiesDTO countryRecord : countryRecords) {
                saveCountryWithCities(countryRecord);
            }
            isLoaded = TRUE;
        } catch (IOException e) {
            log.error("Failed to load countries from JSON file: {}", FILE_PATH, e);
            throw new RuntimeException("Error loading countries from JSON", e);
        } finally {
            setCountriesLoaded(isLoaded);
        }
    }

    private void saveCountryWithCities(CountryWithCitiesDTO countryRecord) {
        if (isFalse(countryRepository.existsByCode(countryRecord.getCode()))) {
            Country country = buildCountry(countryRecord);

            List<City> cities = buildCities(countryRecord.getCities(), country);
            country.setCities(cities);

            countryRepository.save(country);
        }
    }

    private Country buildCountry(CountryWithCitiesDTO countryRecord) {
        return Country.builder()
                .name(countryRecord.getName())
                .code(countryRecord.getCode())
                .build();
    }

    private List<City> buildCities(List<String> cityNames, Country country) {
        List<City> cities = new ArrayList<>();
        if (cityNames != null && !cityNames.isEmpty()) {
            for (String cityName : cityNames) {
                City city = City.builder()
                        .name(cityName)
                        .country(country)
                        .build();
                cities.add(city);
            }
        }
        return cities;
    }

    private boolean isCountriesLoaded() {
        return initializationStatusRepository.findByName(COUNTRIES)
                .map(InitializationStatus::isLoaded)
                .orElse(false);
    }

    private void setCountriesLoaded(boolean isLoaded) {
        InitializationStatus status = InitializationStatus.builder()
                .name(COUNTRIES)
                .isLoaded(isLoaded)
                .build();
        log.info(COUNTRIES + " loaded successfully");
        initializationStatusRepository.save(status);
    }
}
