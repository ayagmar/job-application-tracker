package com.ayagmar.jobapplicationtracker.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static ProblemDetail toProblemDetails(HttpStatus status, String ex, String title) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex);
        problemDetail.setTitle(title);
        problemDetail.setProperty("errorCategory", "Generic Exception");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex) {
        return toProblemDetails(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Entity is Not Found");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        return toProblemDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Illegal Argument Exception");
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleIllegalStateException(IllegalStateException ex) {
        return toProblemDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Illegal State Exception");
    }

    @ExceptionHandler(FieldAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleFieldAlreadyExistsException(FieldAlreadyExists ex) {
        return toProblemDetails(HttpStatus.CONFLICT, ex.getLocalizedMessage(), "Field Already Exists");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ProblemDetail handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return toProblemDetails(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), "Http Method Not Allowed");
    }

}
