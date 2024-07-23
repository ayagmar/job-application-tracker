package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.JobPosting;
import com.ayagmar.jobapplicationtracker.model.record.jobposting.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.model.record.jobposting.JobPostingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobPostingMapper {
    JobPostingResponse toDTO(JobPosting jobPosting);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "city", ignore = true)
    JobPosting toEntity(JobPostingRequest jobPostingRequest);
}
