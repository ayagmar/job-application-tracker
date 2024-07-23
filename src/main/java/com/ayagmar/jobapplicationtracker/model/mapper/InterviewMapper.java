package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.Interview;
import com.ayagmar.jobapplicationtracker.model.record.interview.InterviewRequest;
import com.ayagmar.jobapplicationtracker.model.record.interview.InterviewResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewMapper {
    InterviewResponse toDTO(Interview interview);

    Interview toEntity(InterviewRequest interviewRequest);
}
