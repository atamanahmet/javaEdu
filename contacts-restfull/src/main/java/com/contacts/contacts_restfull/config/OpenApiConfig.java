package com.contacts.contacts_restfull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import com.contacts.contacts_restfull.OpenApi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
        @Bean
        public OpenAPI openApi() {
                return new OpenAPI()
                                .info(new Info().title("Contact API")
                                                .description("Contact managing API")
                                                .version("v0.2.0")
                                                .license(new License().name("Apache 2.0")
                                                                .url("")))
                                .externalDocs(new ExternalDocumentation()
                                                .url("localhost:8080/v3/api-docs"));
        }

}
