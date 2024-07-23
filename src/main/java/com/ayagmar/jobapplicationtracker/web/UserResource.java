package com.ayagmar.jobapplicationtracker.web;

import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.model.record.user.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.user.UserResponse;
import com.ayagmar.jobapplicationtracker.service.UserService;
import com.ayagmar.jobapplicationtracker.web.api.UserApi;
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
public class UserResource implements UserApi {
    private final UserService userService;

    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userCreateDTO) {
        UserResponse createdUser = userService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }


    public ResponseEntity<PaginatedResponse<UserResponse>> getAllUsers(@ParameterObject Pageable pageable) {
        PaginatedResponse<UserResponse> userPage = userService.getAllUsersByPage(pageable);
        return ResponseEntity.ok(userPage);
    }


    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
