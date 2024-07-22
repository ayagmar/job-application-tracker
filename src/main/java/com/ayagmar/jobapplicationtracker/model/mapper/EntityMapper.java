package com.ayagmar.jobapplicationtracker.model.mapper;

import com.ayagmar.jobapplicationtracker.model.InitializationStatus;
import com.ayagmar.jobapplicationtracker.model.record.InitializationStatusCreateDTO;
import com.ayagmar.jobapplicationtracker.model.record.InitializationStatusDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EntityMapper.class})
public interface EntityMapper {

    InitializationStatusDTO toDTO(InitializationStatus initializationStatus);

    InitializationStatus toEntity(InitializationStatusCreateDTO initializationStatusCreateDTO);


}