package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.ResponseDTO;
import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.usecase.RouteUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/routes")
@Tag    (name = "Routes", description = "Endpoints for managing routes")
@CrossOrigin(origins = "*")
public class RouteController {
    private final RouteUseCase routeUseCase;

    public RouteController(RouteUseCase routeUseCase) {
        this.routeUseCase = routeUseCase;
    }

    @PostMapping
    @Operation(summary = "Create a new route", description = "Add a new route to the system.")
    public ResponseEntity<ResponseDTO<RouteDTO>> createRoute(@Valid @RequestBody RouteDTO routeDTO) {
        RouteDTO createdRoute = routeUseCase.createRoute(routeDTO);
        return ResponseEntity.status(201).body(new ResponseDTO<>("Route created successfully", createdRoute, 201));
    }

    @GetMapping
    @Operation(summary = "Get all routes", description = "Retrieve a list of all routes. Optionally, you can search by name.")
    public ResponseEntity<ResponseDTO<List<RouteDTO>>> getAllRoutes() {
        return ResponseEntity.status(200).body(new ResponseDTO<>("Routes retrieved successfully", routeUseCase.getAllRoutes(), 200));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a route", description = "Modify an existing route in the system.")
    public ResponseEntity<ResponseDTO<RouteDTO>> updateRoute(@PathVariable Long id, @Valid @RequestBody RouteDTO routeDTO) {
        routeDTO.setId(id);
        RouteDTO updatedRoute = routeUseCase.updateRoute(routeDTO);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Route updated successfully", updatedRoute, 200));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a route", description = "Remove a route from the system by its unique identifier.")
    public ResponseEntity<ResponseDTO<Void>> deleteRoute(@PathVariable Long id) {
        routeUseCase.deleteRoute(id);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Route deleted successfully", null, 200));
    }

    @GetMapping("/search")
    @Operation(summary = "Search routes by name", description = "Retrieve a list of routes that match the given name.")
    public ResponseEntity<ResponseDTO<List<RouteDTO>>> findRoutesByName(@RequestParam String name) {
        List<RouteDTO> routes = routeUseCase.getRouteByName(name);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Routes retrieved successfully", routes, 200));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get route by ID", description = "Retrieve a route by its unique identifier.")
    public ResponseEntity<ResponseDTO<RouteDTO>> getRouteById(@PathVariable Long id) {
        RouteDTO route = routeUseCase.getRouteById(id);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Route retrieved successfully", route, 200));
    }

}
