package com.ayagmar.jobapplicationtracker.user.service.mapper;

import com.ayagmar.jobapplicationtracker.user.domain.User;
import com.ayagmar.jobapplicationtracker.user.model.UserRequest;
import com.ayagmar.jobapplicationtracker.user.model.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDTO(User user);

    User toEntity(UserRequest userRequest);
}
