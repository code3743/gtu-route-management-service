package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.usecase.RouteUseCase;
import com.gtu.route_management_service.application.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        RouteDTO createdRoute = routeUseCase.createRoute(routeDTO);
        return ResponseEntity.status(201).body(new ResponseDTO("Route created successfully", createdRoute, 201));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllRoutes() {
        List<RouteDTO> routes = routeUseCase.getAllRoutes();
        return ResponseEntity.ok(new ResponseDTO("Routes retrieved successfully", routes, 200));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateRoute(@PathVariable Long id, @Valid @RequestBody RouteDTO routeDTO) {
        routeDTO.setId(id);
        RouteDTO updatedRoute = routeUseCase.updateRoute(routeDTO);
        return ResponseEntity.ok(new ResponseDTO("Route updated successfully", updatedRoute, 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteRoute(@PathVariable Long id) {
        routeUseCase.deleteRoute(id);
        return ResponseEntity.ok(new ResponseDTO("Route deleted successfully", null, 200));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> getRouteByName(@RequestParam String name) {
        List<RouteDTO> routes = routeUseCase.getRouteByName(name);
        return ResponseEntity.ok(new ResponseDTO("Routes retrieved successfully", routes, 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getRouteById(@PathVariable Long id) {
        RouteDTO route = routeUseCase.getRouteById(id);
        return ResponseEntity.ok(new ResponseDTO("Route retrieved successfully", route, 200));
    }

}
