package com.ayagmar.jobapplicationtracker.web.api;

import com.ayagmar.jobapplicationtracker.model.record.document.DocumentRequest;
import com.ayagmar.jobapplicationtracker.model.record.document.DocumentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/documents")
@Tag(name = "Document", description = "the document API")
public interface DocumentApi {
    @Operation(summary = "Create document", description = "Creates a new document")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Document created successfully")})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<DocumentResponse> createDocument(
            @Parameter(description = "Attached Document file") @RequestParam("file") MultipartFile file,
            @Parameter(description = "Document creation details") @Valid @RequestBody DocumentRequest documentRequest);

    @Operation(summary = "Fetch a document by id", description = "Retrieves a document by Id from the db")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/{id}")
    ResponseEntity<DocumentResponse> getDocumentById(@PathVariable Long id);


    @Operation(summary = "Delete a document by id", description = "Deletes a document by Id from the db")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "successful operation")})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDocument(@Parameter(description = "Existing document Id") @PathVariable Long id);


    @Operation(summary = "Downloads a document's data by id", description = "Downloads document")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation")})
    @GetMapping("/{id}/data")
    ResponseEntity<byte[]> getDocumentData(@Parameter(description = "Existing document Id") @PathVariable Long id);

}
