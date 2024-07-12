package com.ayagmar.jobapplicationtracker.model.record;

public record NotificationRecord(Long id, Long userId, String message, boolean read) {
}
