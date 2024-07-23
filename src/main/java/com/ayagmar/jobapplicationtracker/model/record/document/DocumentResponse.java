package com.ayagmar.jobapplicationtracker.model.record.document;

import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.enums.DocumentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentResponse implements Serializable {
    private Long id;
    private String fileName;
    private DocumentType type;
    @JsonIgnore
    private User user;
}