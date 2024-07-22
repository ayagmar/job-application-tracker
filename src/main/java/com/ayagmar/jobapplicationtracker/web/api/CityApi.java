package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.CityResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/cities")
public interface CityApi {
    @PostMapping
    ResponseEntity<CityResponse> createCity(@RequestBody CityRequest cityRequest);

    @GetMapping
    ResponseEntity<PaginatedResponse<CityResponse>> getAllCities(@ParameterObject Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<CityResponse> getCityById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCity(@PathVariable Long id);

}
