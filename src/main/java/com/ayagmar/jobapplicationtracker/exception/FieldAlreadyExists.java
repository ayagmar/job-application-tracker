package com.ayagmar.jobapplicationtracker.exception;


public class FieldAlreadyExists extends RuntimeException {
    public FieldAlreadyExists(String message) {
        super(message);
    }
}