package com.ayagmar.jobapplicationtracker.jobposting.service.mapper;

import com.ayagmar.jobapplicationtracker.jobposting.domain.JobPosting;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.jobposting.model.JobPostingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobPostingMapper {
    JobPostingResponse toDTO(JobPosting jobPosting);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "city", ignore = true)
    JobPosting toEntity(JobPostingRequest jobPostingRequest);
}
