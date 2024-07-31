package com.ayagmar.jobapplicationtracker.document.service.mapper;

import com.ayagmar.jobapplicationtracker.document.domain.Document;
import com.ayagmar.jobapplicationtracker.document.model.DocumentRequest;
import com.ayagmar.jobapplicationtracker.document.model.DocumentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentResponse toDTO(Document document);

    Document toEntity(DocumentRequest documentResponse);
}
