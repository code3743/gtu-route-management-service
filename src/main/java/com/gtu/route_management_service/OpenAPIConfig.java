package com.gtu.route_management_service;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Mi API", version = "1.0"),
    servers = {
        @Server(url = "${SWAGGER_SERVER_URL:http://localhost/api/route-management}", description = "Server URL")
    }
)
public class OpenAPIConfig {
}
