package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.exception.FieldAlreadyExists;
import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.enums.DocumentType;
import com.ayagmar.jobapplicationtracker.model.mapper.UserMapper;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponse;
import com.ayagmar.jobapplicationtracker.model.record.PaginatedResponseFactory;
import com.ayagmar.jobapplicationtracker.model.record.user.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.user.UserResponse;
import com.ayagmar.jobapplicationtracker.repository.DocumentRepository;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        validateUsername(userRequest.getUsername());
        var user = mapper.toEntity(userRequest);
        user = userRepository.save(user);
        log.info("User created with username: {}", user.getUsername());
        return mapper.toDTO(user);
    }


    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User " + id + " is not found"));
        return mapper.toDTO(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        getUserById(id);
        userRepository.deleteById(id);
        log.info("User with id " + id + " is deleted");
    }

    @Transactional(readOnly = true)
    public PaginatedResponse<UserResponse> getAllUsersByPage(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        log.info("Retrieved {} users", users.getTotalElements());
        Page<UserResponse> userResponsePage = users.map(mapper::toDTO);
        return PaginatedResponseFactory.createFrom(userResponsePage);
    }


    private void validateUsername(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new FieldAlreadyExists("Username already exists: " + username);
        });
    }

    public UserResponse addDocumentToUser(Long userId, DocumentType documentType, Long documentId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        var document = documentRepository.findById(documentId)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        if (document.getType() != documentType) {
            throw new IllegalArgumentException("Document type mismatch");
        }
        user.addDocument(documentType, documentId);
        var savedUser = userRepository.save(user);
        return mapper.toDTO(savedUser);
    }

    public UserResponse removeDocumentFromUser(Long userId, DocumentType documentType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.removeDocument(documentType);
        User savedUser = userRepository.save(user);
        return mapper.toDTO(savedUser);
    }

}
