package com.ayagmar.jobapplicationtracker.model.record;

import lombok.Data;

@Data
public class InitializationStatusDTO {
    private Long id;
    private String name;
    private boolean isLoaded;
}
