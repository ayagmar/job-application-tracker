package com.ayagmar.jobapplicationtracker.model;

import lombok.Getter;

@Getter
public enum ApplicationStatus {
    APPLIED,
    UNDER_REVIEW,
    INTERVIEW_SCHEDULED,
    OFFER_RECEIVED,
    REJECTED,
    CANCELLED
}