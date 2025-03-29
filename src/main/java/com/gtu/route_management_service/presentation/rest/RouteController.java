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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routeUseCase.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RouteDTO>> findRoutesByName(@RequestParam String name) {
        List<RouteDTO> routes = routeUseCase.getRouteByName(name);
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getRouteById(@PathVariable Long id) {
        RouteDTO route = routeUseCase.getRouteById(id);
        return ResponseEntity.ok(route);
    }

}
