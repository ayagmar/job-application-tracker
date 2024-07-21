package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.model.record.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.CityResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityResource {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponse> createCity(@RequestBody CityRequest cityRequest) {
        CityResponse createdCity = cityService.createCity(cityRequest);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PaginatedResponse<CityResponse>> getAllCities(@ParameterObject Pageable pageable) {
        PaginatedResponse<CityResponse> cityPage = cityService.getAllCities(pageable);
        return new ResponseEntity<>(cityPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) {
        CityResponse userResponse = cityService.getCityById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
