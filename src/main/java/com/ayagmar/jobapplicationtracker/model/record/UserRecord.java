package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserRecord(Long id, String username, String password, String fullName) implements Serializable {
    public User toUser() {
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .fullName(fullName)
                .build();
    }
}