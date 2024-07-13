package com.ayagmar.jobapplicationtracker.configuration;

import com.ayagmar.jobapplicationtracker.model.Country;
import com.ayagmar.jobapplicationtracker.model.record.CountryWithCitiesDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Country.class, CountryWithCitiesDTO.class)
                .addMappings(mapper -> mapper.skip(CountryWithCitiesDTO::setCities));
        return modelMapper;
    }
}