package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.model.record.city.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.city.CityResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "City", description = "the city API")
public interface CityApi {
    @Operation(summary = "Create city", description = "Creates a new city")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "City created successfully")})
    @PostMapping
    ResponseEntity<CityResponse> createCity(@Parameter(description = "Created City object") @Valid @RequestBody CityRequest cityRequest);

    @Operation(summary = "Fetch a city by id", description = "Retrieves a city by Id from the Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping
    ResponseEntity<PaginatedResponse<CityResponse>> getAllCities(@ParameterObject Pageable pageable);

    @Operation(summary = "Fetch a paginated cities list from database", description = "Retrieves cities pageable from Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/{id}")
    ResponseEntity<CityResponse> getCityById(@PathVariable Long id);

    @Operation(summary = "Delete city", description = "Deletes a city from the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "successful operation")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCity(@PathVariable Long id);

}
