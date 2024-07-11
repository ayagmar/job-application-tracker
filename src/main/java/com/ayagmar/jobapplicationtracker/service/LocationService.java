package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.City;
import com.ayagmar.jobapplicationtracker.model.Country;
import com.ayagmar.jobapplicationtracker.model.record.CountryRecord;
import com.ayagmar.jobapplicationtracker.model.InitializationStatus;
import com.ayagmar.jobapplicationtracker.repository.CityRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocationService {

    public static final String FILE_PATH = "countries.json";
    private final CountryRepository countryRepository;
    private final InitializationStatusRepository initializationStatusRepository;
    private final CityRepository cityRepository;

    @PostConstruct
    public void init() {
        if (!isCountriesLoaded()) {
            loadCountriesFromJson();
            setCountriesLoaded();
        }
    }

    private void loadCountriesFromJson() {
        try (InputStream inputStream = new ClassPathResource(FILE_PATH).getInputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<CountryRecord> countryRecords = objectMapper.readValue(inputStream, new TypeReference<>() {
            });

            for (CountryRecord countryRecord : countryRecords) {
                saveCountryWithCities(countryRecord);
            }
        } catch (IOException e) {
            log.error("Failed to load countries from JSON file: {}", FILE_PATH, e);
            throw new RuntimeException("Error loading countries from JSON", e);
        }
    }

    private void saveCountryWithCities(CountryRecord countryRecord) {
        if (!countryRepository.existsByCode(countryRecord.code())) {
            Country country = buildCountry(countryRecord);

            List<City> cities = buildCities(countryRecord.cities(), country);
            country.setCities(cities);

            countryRepository.save(country);
        }
    }

    private Country buildCountry(CountryRecord countryRecord) {
        return Country.builder()
                .name(countryRecord.name())
                .code(countryRecord.code())
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
        return initializationStatusRepository.findByName("countries")
                .map(InitializationStatus::isLoaded)
                .orElse(false);
    }

    private void setCountriesLoaded() {
        InitializationStatus status = InitializationStatus.builder()
                .name("countries")
                .isLoaded(true)
                .build();
        initializationStatusRepository.save(status);
    }
}
