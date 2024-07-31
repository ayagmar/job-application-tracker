package com.ayagmar.jobapplicationtracker.document.rest;

import com.ayagmar.jobapplicationtracker.document.model.DocumentRequest;
import com.ayagmar.jobapplicationtracker.document.model.DocumentResponse;
import com.ayagmar.jobapplicationtracker.document.rest.api.DocumentApi;
import com.ayagmar.jobapplicationtracker.document.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DocumentResource implements DocumentApi {
    private final DocumentService documentService;

    @Override
    public ResponseEntity<DocumentResponse> createDocument(
            @RequestParam("file") MultipartFile file,
            @Valid @ModelAttribute DocumentRequest documentRequest) {
        DocumentResponse createdDocument = documentService.createDocument(file, documentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocument);
    }


    @Override
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable Long id) {
        DocumentResponse document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }


    @Override
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<byte[]> getDocumentData(@PathVariable Long id) {
        byte[] documentData = documentService.getDocumentData(id);
        DocumentResponse document = documentService.getDocumentById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(documentData);
    }
}
