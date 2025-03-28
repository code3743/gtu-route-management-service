package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.usecase.RouteUseCase;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/routes")
public class RouteController {
    private final RouteUseCase routeUseCase;

    public RouteController(RouteUseCase routeUseCase) {
        this.routeUseCase = routeUseCase;
    }

    @PostMapping
    public ResponseEntity<RouteDTO> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        RouteDTO createdRoute = routeUseCase.createRoute(routeDTO);
        return ResponseEntity.status(201).body(createdRoute);
    }

    @GetMapping
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        return ResponseEntity.ok(routeUseCase.getAllRoutes());          
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteDTO> updateRoute(@PathVariable Long id, @Valid @RequestBody RouteDTO routeDTO) {
        routeDTO.setId(id);
        RouteDTO updatedRoute = routeUseCase.updateRoute(routeDTO);
        return ResponseEntity.ok(updatedRoute);
    }


}
