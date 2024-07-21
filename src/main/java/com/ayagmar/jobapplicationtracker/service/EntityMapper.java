package com.ayagmar.jobapplicationtracker.service;

import com.ayagmar.jobapplicationtracker.model.City;
import com.ayagmar.jobapplicationtracker.model.Company;
import com.ayagmar.jobapplicationtracker.model.Country;
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
import com.ayagmar.jobapplicationtracker.model.record.SimpleCountryResponse;
import com.ayagmar.jobapplicationtracker.model.record.UserRequest;
import com.ayagmar.jobapplicationtracker.model.record.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EntityMapper.class})
public interface EntityMapper {
    UserResponse toDTO(User user);

    User toEntity(UserRequest userRequest);

    CityResponse toDTO(City city);

    SimpleCountryResponse toDTO(Country country);

    City toEntity(CityRequest city);

    CompanyResponse toDTO(Company company);

    Company toEntity(CompanyRequest companyRequest);

    InterviewResponse toDTO(Interview interview);

    Interview toEntity(InterviewRequest interviewRequest);

    JobApplicationResponse toDTO(JobApplication jobApplication);


    @Mapping(target = "user", ignore = true)
    @Mapping(target = "jobPosting", ignore = true)
    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "applicationDate", ignore = true)
    @Mapping(target = "lastStatusChangeDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    JobApplication toEntity(JobApplicationRequest jobApplicationRequest);


    JobPostingResponse toDTO(JobPosting jobPosting);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "city", ignore = true)
    JobPosting toEntity(JobPostingRequest jobPostingRequest);

    InitializationStatusDTO toDTO(InitializationStatus initializationStatus);

    InitializationStatus toEntity(InitializationStatusCreateDTO initializationStatusCreateDTO);

    DocumentResponse toDTO(Document document);

    Document toEntity(DocumentRequest documentResponse);

}