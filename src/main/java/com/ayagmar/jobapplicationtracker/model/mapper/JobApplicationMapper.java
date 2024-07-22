package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.JobApplication;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationResponse;
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
