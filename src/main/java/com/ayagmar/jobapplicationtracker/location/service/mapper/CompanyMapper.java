package com.ayagmar.jobapplicationtracker.location.service.mapper;

import com.ayagmar.jobapplicationtracker.location.domain.Company;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.location.model.company.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponse toDTO(Company company);

    Company toEntity(CompanyRequest companyRequest);
}
