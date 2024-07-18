package com.ayagmar.jobapplicationtracker.model.record;

import org.springframework.data.domain.Page;

public class PaginatedResponseFactory {
    public static <T> PaginatedResponse<T> createFrom(Page<T> page) {
        return PaginatedResponse.<T>builder()
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .response(page.getContent())
                .build();
    }
}