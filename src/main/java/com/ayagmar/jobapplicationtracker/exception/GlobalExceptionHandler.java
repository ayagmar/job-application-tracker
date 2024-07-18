package com.ayagmar.jobapplicationtracker.exception;


import com.ayagmar.jobapplicationtracker.model.record.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(), "Entity not found",
                HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(), "Illegal Argument Exception",
                HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private static ErrorResponse buildErrorResponse(String exceptionMessage, String details, int status) {
        return new ErrorResponse(exceptionMessage,
                details, status, LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(), "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FieldAlreadyExists.class, IllegalStateException.class})
    public ResponseEntity<ErrorResponse> handleFieldAlreadyExistsException(FieldAlreadyExists ex) {
        ErrorResponse errorResponse = buildErrorResponse(ex.getMessage(), "Unique Constraint breached",
                HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
