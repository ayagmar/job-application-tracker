package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.service.CountryService;
import com.ayagmar.jobapplicationtracker.web.api.CountryApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CountryResource implements CountryApi {
    private final CountryService countryService;
}
