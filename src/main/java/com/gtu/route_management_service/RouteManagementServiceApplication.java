package com.gtu.route_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
public class RouteManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouteManagementServiceApplication.class, args);
    }
}