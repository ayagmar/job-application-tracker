package com.ayagmar.jobapplicationtracker.document.model;

import com.ayagmar.jobapplicationtracker.document.domain.DocumentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentRequest implements Serializable {
    @NotNull
    private DocumentType type;
    @NotNull
    private Long userId;
}
