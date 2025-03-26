package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.RouteDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/routes")
public class RouteController {
    @PostMapping
    public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO routeDTO){
        return ResponseEntity.ok("Route created successfully: " +  routeDTO.getName());
    }
    
}
