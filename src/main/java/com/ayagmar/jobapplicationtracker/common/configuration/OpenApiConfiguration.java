package com.ayagmar.jobapplicationtracker.common.configuration;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springdoc.core.utils.Constants.ALL_PATTERN;

@Configuration
public class OpenApiConfiguration {

    private static Info buildInfo(String title, String appVersion) {
        return new Info().title(title).version(appVersion);
    }

    @Bean
    public GroupedOpenApi actuatorApi(OpenApiCustomizer actuatorOpenApiCustomizer,
                                      WebEndpointProperties endpointProperties,
                                      @Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Actuator")
                .pathsToMatch(endpointProperties.getBasePath() + ALL_PATTERN)
                .addOpenApiCustomizer(actuatorOpenApiCustomizer)
                .addOpenApiCustomizer(openApi -> openApi.info(buildInfo("Actuator API", appVersion)))
                .build();
    }

    @Bean
    public GroupedOpenApi usersGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Users")
                .addOpenApiCustomizer(openApi -> openApi.info(buildInfo("Users API", appVersion)))
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi citiesGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Cities")
                .addOpenApiCustomizer(openApi -> openApi.info(buildInfo("Cities API", appVersion)))
                .pathsToMatch("/api/cities/**")
                .build();
    }

    @Bean
    public GroupedOpenApi JobPostingsGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Job Postings")
                .addOpenApiCustomizer(openApi -> openApi.info(buildInfo("JobPosting API", appVersion)))
                .pathsToMatch("/api/jobs/**")
                .build();
    }

    @Bean
    public GroupedOpenApi JobApplicationsGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Job Applications")
                .addOpenApiCustomizer(openApi -> openApi.info(buildInfo("Job applications API", appVersion)))
                .pathsToMatch("/api/applications/**")
                .build();
    }

    @Bean
    public GroupedOpenApi DocumentsGroup(@Value("${springdoc.version}") String appVersion) {
        return GroupedOpenApi.builder()
                .group("Documents")
                .addOpenApiCustomizer(openApi -> openApi.info(buildInfo("Documents API", appVersion)))
                .pathsToMatch("/api/documents/**")
                .build();
    }

}
