package com.ayagmar.jobapplicationtracker.model;

import java.time.LocalDateTime;

public record ErrorResponse(String message, String details, LocalDateTime timestamp) {
}
