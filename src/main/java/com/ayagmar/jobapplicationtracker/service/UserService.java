package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.exception.UsernameAlreadyExistsException;
import com.ayagmar.jobapplicationtracker.model.record.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.UserResponse;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntityMapper mapper;

    public UserResponse createUser(UserRequest userRequest) {
        validateUsername(userRequest.getUsername());
        var user = mapper.toEntity(userRequest);
        user = userRepository.save(user);
        return mapper.toDTO(user);
    }

    private void validateUsername(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new UsernameAlreadyExistsException("Username already exists: " + username);
        });
    }

    public UserResponse getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " is not found"));
        return mapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
        log.info("User with id " + id + " is been deleted");
    }
}
