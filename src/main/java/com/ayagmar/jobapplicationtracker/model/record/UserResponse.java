package com.ayagmar.jobapplicationtracker.model.record;

import com.ayagmar.jobapplicationtracker.model.DocumentType;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class UserResponse implements Serializable {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private Map<DocumentType, Long> documents;
}
