package com.ayagmar.jobapplicationtracker.model.record.document;

import com.ayagmar.jobapplicationtracker.model.enums.DocumentType;
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
