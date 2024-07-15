package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.record.UserCreateDTO;
import com.ayagmar.jobapplicationtracker.model.record.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserCreateDTO userCreateDTO);
}