package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.Company;
import com.ayagmar.jobapplicationtracker.model.record.company.CompanyRequest;
import com.ayagmar.jobapplicationtracker.model.record.company.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyResponse toDTO(Company company);

    Company toEntity(CompanyRequest companyRequest);
}
