package com.cathaybk.newbiehomework.infra;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Internal API")
                .packagesToScan("com.cathaybk.newbiehomework.controller")
                .build();
    }
}
