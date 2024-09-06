package com.secuenciaDNA.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customSwagger(@Value("${app.name}") String appName,
                                 @Value("${app.version}") String version,
                                 @Value("${app.contact}") String contact,
                                 @Value("${app.email}") String email,
                                 @Value("${app.description}") String description) {

        return new OpenAPI().info(new Info().title(appName)
                .version(version)
                .description(description)
                .contact(new Contact().name(contact)
                        .email(email)));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan("com.secuenciaDNA.controllers") // Cambia esto a tu paquete de controladores
                .build();
    }
}
