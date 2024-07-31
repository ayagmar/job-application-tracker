package com.ayagmar.jobapplicationtracker.document.model;

import com.ayagmar.jobapplicationtracker.document.domain.DocumentType;
import com.ayagmar.jobapplicationtracker.user.domain.User;
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