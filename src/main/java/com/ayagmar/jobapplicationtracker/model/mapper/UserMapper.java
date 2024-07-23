package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.record.user.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toDTO(User user);

    User toEntity(UserRequest userRequest);
}
