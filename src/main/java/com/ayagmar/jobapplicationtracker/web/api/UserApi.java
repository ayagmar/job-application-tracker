package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.model.record.user.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.user.UserResponse;
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

@RequestMapping("/api/users")
@Tag(name = "User", description = "the user API")
public interface UserApi {

    @Operation(summary = "Create user", description = "Creates a new user")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "User created successfully")})
    @PostMapping
    ResponseEntity<UserResponse> createUser(@Parameter(description = "Created user object") @Valid @RequestBody UserRequest userRequest);

    @Operation(summary = "Fetch a user by id", description = "Retrieves a user by Id from the Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/{id}")
    ResponseEntity<UserResponse> getUserById(@Parameter(description = "Existing User Id") @PathVariable Long id);

    @Operation(summary = "Fetch a paginated list from database", description = "Retrieves users pageable from Database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping
    ResponseEntity<PaginatedResponse<UserResponse>> getAllUsers(@ParameterObject Pageable pageable);

    @Operation(summary = "Delete user", description = "Deletes a user from the database")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "successful operation")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@Parameter(description = "Existing User Id") @PathVariable Long id);
}
