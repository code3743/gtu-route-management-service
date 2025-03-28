package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.mapper.RouteMapper;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/routes")
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<String> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        Route route = RouteMapper.toDomain(routeDTO);
        routeService.validateRoute(route);

        Route savedRoute = routeService.saveRoute(route);

        return ResponseEntity.ok("Route created successfully: " +  savedRoute.getName());
    }
    
}
