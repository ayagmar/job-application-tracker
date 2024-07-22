package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.Document;
import com.ayagmar.jobapplicationtracker.model.record.DocumentRequest;
import com.ayagmar.jobapplicationtracker.model.record.DocumentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentResponse toDTO(Document document);

    Document toEntity(DocumentRequest documentResponse);
}
