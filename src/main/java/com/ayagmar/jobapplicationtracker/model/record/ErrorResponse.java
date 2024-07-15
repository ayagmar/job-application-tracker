package com.ayagmar.jobapplicationtracker.model.record;

import java.time.LocalDateTime;

public record ErrorResponse(String message, String details, int status, LocalDateTime timestamp) {
}
