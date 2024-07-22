package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.model.record.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.CityResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.service.CityService;
import com.ayagmar.jobapplicationtracker.web.api.CityApi;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CityResource implements CityApi {

    private final CityService cityService;

    @Override
    public ResponseEntity<CityResponse> createCity(@RequestBody CityRequest cityRequest) {
        CityResponse createdCity = cityService.createCity(cityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCity);
    }

    @Override
    public ResponseEntity<PaginatedResponse<CityResponse>> getAllCities(@ParameterObject Pageable pageable) {
        PaginatedResponse<CityResponse> cityPage = cityService.getAllCities(pageable);
        return ResponseEntity.ok(cityPage);
    }

    @Override
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) {
        CityResponse userResponse = cityService.getCityById(id);
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

}
