package com.ayagmar.jobapplicationtracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class FieldAlreadyExists extends RuntimeException {
    public FieldAlreadyExists(String message) {
        super(message);
    }
}