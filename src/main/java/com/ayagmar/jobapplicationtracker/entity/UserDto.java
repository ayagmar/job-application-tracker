package com.ayagmar.jobapplicationtracker.entity;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id, String username, String password, String fullName) implements Serializable {
    public User toUser() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .fullName(fullName)
                .build();
    }
}