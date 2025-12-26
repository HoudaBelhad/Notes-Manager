package com.example.hbdev.notes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notesOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Notes API")
                        .description("REST API for managing notes (CRUD + date filter)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Houda Belhad")
                                .email("houdabelhad6@gmail.com")
                        )
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")
                        )
                );
    }
}
