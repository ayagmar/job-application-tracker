package com.ayagmar.jobapplicationtracker.jobapplication.service.mapper;

import com.ayagmar.jobapplicationtracker.jobapplication.domain.JobApplication;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.jobapplication.model.JobApplicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobApplicationMapper {
    JobApplicationResponse toDTO(JobApplication jobApplication);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "jobPosting", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "lastStatusChangeDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    JobApplication toEntity(JobApplicationRequest jobApplicationRequest);
}
