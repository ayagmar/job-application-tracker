package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.exception.EntityNotFoundException;
import com.ayagmar.jobapplicationtracker.model.Document;
import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.mapper.DocumentMapper;
import com.ayagmar.jobapplicationtracker.model.record.DocumentRequest;
import com.ayagmar.jobapplicationtracker.model.record.DocumentResponse;
import com.ayagmar.jobapplicationtracker.repository.DocumentRepository;
import com.ayagmar.jobapplicationtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final DocumentMapper documentMapper;

    @Transactional
    public DocumentResponse createDocument(MultipartFile file, DocumentRequest documentRequest) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required and must not be empty");
        }
        User user = userRepository.findById(documentRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (user.getDocuments().containsKey(documentRequest.getType())) {
            throw new IllegalStateException("A document of type " + documentRequest.getType() + " already exists for this user");
        }
        try {
            Document document = Document.builder()
                    .fileName(file.getOriginalFilename())
                    .type(documentRequest.getType())
                    .data(file.getBytes())
                    .user(user)
                    .build();

            Document savedDocument = documentRepository.save(document);

            user.addDocument(document.getType(), savedDocument.getId());
            userRepository.save(user);

            return documentMapper.toDTO(savedDocument);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public DocumentResponse getDocumentById(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        return documentMapper.toDTO(document);
    }

    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));

        User user = document.getUser();
        user.removeDocument(document.getType());
        userRepository.save(user);

        documentRepository.delete(document);
    }

    public byte[] getDocumentData(Long id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Document not found"));
        return document.getData();
    }
}
