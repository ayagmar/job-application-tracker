package com.ayagmar.jobapplicationtracker.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class PaginatedResponse<T> implements Serializable {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    private List<T> response;
}
