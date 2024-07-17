package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.City;
import com.ayagmar.jobapplicationtracker.model.Company;
import com.ayagmar.jobapplicationtracker.model.Document;
import com.ayagmar.jobapplicationtracker.model.InitializationStatus;
import com.ayagmar.jobapplicationtracker.model.Interview;
import com.ayagmar.jobapplicationtracker.model.JobApplication;
import com.ayagmar.jobapplicationtracker.model.JobPosting;
import com.ayagmar.jobapplicationtracker.model.User;
import com.ayagmar.jobapplicationtracker.model.record.CityRequest;
import com.ayagmar.jobapplicationtracker.model.record.CityResponse;
import com.ayagmar.jobapplicationtracker.model.record.CompanyRequest;
import com.ayagmar.jobapplicationtracker.model.record.CompanyResponse;
import com.ayagmar.jobapplicationtracker.model.record.DocumentRequest;
import com.ayagmar.jobapplicationtracker.model.record.DocumentResponse;
import com.ayagmar.jobapplicationtracker.model.record.InitializationStatusCreateDTO;
import com.ayagmar.jobapplicationtracker.model.record.InitializationStatusDTO;
import com.ayagmar.jobapplicationtracker.model.record.InterviewRequest;
import com.ayagmar.jobapplicationtracker.model.record.InterviewResponse;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobApplicationResponse;
import com.ayagmar.jobapplicationtracker.model.record.JobPostingRequest;
import com.ayagmar.jobapplicationtracker.model.record.JobPostingResponse;
import com.ayagmar.jobapplicationtracker.model.record.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EntityMapper.class})
public interface EntityMapper {
    UserResponse toDTO(User user);

    User toEntity(UserRequest userRequest);

    CityResponse toDTO(City city);

    City toEntity(CityRequest city);

    CompanyResponse toDTO(Company company);

    Company toEntity(CompanyRequest companyRequest);

    InterviewResponse toDTO(Interview interview);

    Interview toEntity(InterviewRequest interviewRequest);

    JobApplicationResponse toDTO(JobApplication jobApplication);

    JobApplication toEntity(JobApplicationRequest jobApplicationRequest);

    JobPostingResponse toDTO(JobPosting jobPosting);

    JobPosting toEntity(JobPostingRequest jobPostingRequest);

    InitializationStatusDTO toDTO(InitializationStatus initializationStatus);

    InitializationStatus toEntity(InitializationStatusCreateDTO initializationStatusCreateDTO);

    DocumentResponse toDTO(Document document);

    Document toEntity(DocumentRequest documentResponse);

}