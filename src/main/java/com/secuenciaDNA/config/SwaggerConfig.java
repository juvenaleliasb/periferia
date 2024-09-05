package com.secuenciaDNA.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
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
}
