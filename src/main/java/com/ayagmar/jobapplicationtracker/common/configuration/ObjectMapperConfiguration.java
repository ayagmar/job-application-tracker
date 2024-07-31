package com.ayagmar.jobapplicationtracker.common.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.FALSE);
        objectMapper.registerModule(new JsonNullableModule());
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Bean
    public JacksonDecoder jacksonDecoder() {
        return new JacksonDecoder(objectMapper());
    }

    @Bean
    public JacksonEncoder jacksonEncoder() {
        return new JacksonEncoder(objectMapper());
    }

}
