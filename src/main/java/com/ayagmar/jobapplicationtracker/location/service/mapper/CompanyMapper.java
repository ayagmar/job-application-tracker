package com.ayagmar.jobapplicationtracker.location.service.mapper;

import com.ayagmar.go.jobscraper.model.ScraperJobPosting;
import com.ayagmar.jobapplicationtracker.location.domain.Company;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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

    @Mapping(target = "name", source = "companyDetails.name")
    @Mapping(target = "industry", source = "companyDetails.industry")
    @Mapping(target = "website", source = "companyDetails.url")
    CompanyRequest toCompanyRequest(ScraperJobPosting jobListingResponse);

}
