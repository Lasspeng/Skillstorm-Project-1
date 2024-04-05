package com.skillstorm.project1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition (
    info = @Info(
        contact = @Contact(
            name = "Gavin Liburd",
            email = "gliburd@skillstorm.com"
        ),
        description = "Open API documentation for Project 1",
        title = "API Endpoint Documentation"
    ),
    servers = @Server (
        description = "Local Dev Enviornment",
        url = "http://localhost:8080/"
    )
)
public class OpenApiConfig {
    
}
