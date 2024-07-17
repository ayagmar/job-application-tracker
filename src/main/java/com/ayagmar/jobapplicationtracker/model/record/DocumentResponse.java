package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.DocumentType;
import com.ayagmar.jobapplicationtracker.model.User;
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