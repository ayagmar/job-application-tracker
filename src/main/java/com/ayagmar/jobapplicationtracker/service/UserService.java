package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.record.UserDTO;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final GenericMapper mapper;

    public UserDTO findUserRecordById(Long userId) {
        return userRepository.
                findById(userId).
                map(user -> mapper.toDto(user, UserDTO.class))
                .orElse(null);
    }

    public UserDTO insertUserRecord(UserDTO userRecord) {
        User user = mapper.toEntity(userRecord, User.class);
        return mapper.toEntity(userRepository.save(user), UserDTO.class);
    }
}
