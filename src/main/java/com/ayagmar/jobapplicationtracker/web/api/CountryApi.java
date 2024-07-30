package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.country.CountryRequest;
import com.ayagmar.jobapplicationtracker.model.record.country.CountryResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/countries")
@Tag(name = "Country", description = "the country API")
public interface CountryApi {
    @PostMapping
    default ResponseEntity<CountryResponse> createCountry(CountryRequest countryRequest) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
}
