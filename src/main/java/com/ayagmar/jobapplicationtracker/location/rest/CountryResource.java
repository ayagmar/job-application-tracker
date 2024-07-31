package com.ayagmar.jobapplicationtracker.location.rest;

import com.ayagmar.jobapplicationtracker.location.rest.api.CountryApi;
import com.ayagmar.jobapplicationtracker.location.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryResource implements CountryApi {
    private final CountryService countryService;
}
