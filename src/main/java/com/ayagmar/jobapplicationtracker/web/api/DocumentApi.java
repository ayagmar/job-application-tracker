package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.DocumentRequest;
import com.ayagmar.jobapplicationtracker.model.record.DocumentResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/documents")
public interface DocumentApi {
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<DocumentResponse> createDocument(
            @RequestParam("file") MultipartFile file,
            @Valid @ModelAttribute DocumentRequest documentRequest);

    @GetMapping("/{id}")
    ResponseEntity<DocumentResponse> getDocumentById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDocument(@PathVariable Long id);

    @GetMapping("/{id}/data")
    ResponseEntity<byte[]> getDocumentData(@PathVariable Long id);

}
