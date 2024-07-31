package com.ayagmar.jobapplicationtracker.location.service.mapper;

import com.ayagmar.jobapplicationtracker.location.domain.Company;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import com.ayagmar.jobscraper.model.JobListingResponse;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "industry", target = "industry")
    @Mapping(source = "website", target = "website")
    Company toCompany(CompanyRequest companyRequest);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "industry", target = "industry")
    @Mapping(source = "website", target = "website")
    Company toCompany(CompanyResponse companyResponse);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "industry", target = "industry")
    @Mapping(source = "website", target = "website")
    CompanyResponse toCompanyResponse(Company company);

    @Mapping(source = "company", target = "name")
    @Mapping(source = "industry", target = "industry")
    @Mapping(target = "website", expression = "java(mapCompanyDetailsUrl(jobListingResponse))")
    CompanyRequest toCompanyRequest(JobListingResponse jobListingResponse);

    default String mapJsonNullableToString(JsonNullable<String> jsonNullable) {
        return jsonNullable.orElse(StringUtils.EMPTY);
    }

    default String mapCompanyDetailsUrl(JobListingResponse jobListingResponse) {
        return jobListingResponse.getCompanyUrl()
                .orElse(jobListingResponse.getCompanyDetailsUrl()
                        .orElse(StringUtils.EMPTY));
    }
}
