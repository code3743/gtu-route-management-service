package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.application.usecase.NeighborhoodUseCase;
import com.gtu.route_management_service.application.dto.ResponseDTO;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodUseCase neighborhoodUseCase;

    public NeighborhoodController(NeighborhoodUseCase neighborhoodUseCase) {
        this.neighborhoodUseCase = neighborhoodUseCase;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllNeighborhoods() {
        List<NeighborhoodDTO> neighborhoods = neighborhoodUseCase.getAllNeighborhoods();
        return ResponseEntity.ok(new ResponseDTO("Neighborhoods retrieved successfully", neighborhoods, 200));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getNeighborhoodById(@PathVariable Long id) {
        NeighborhoodDTO neighborhood = neighborhoodUseCase.getNeighborhoodById(id);
        return ResponseEntity.ok(new ResponseDTO("Neighborhood retrieved successfully", neighborhood, 200));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        NeighborhoodDTO createdNeighborhood = neighborhoodUseCase.createNeighborhood(neighborhoodDTO);
        return ResponseEntity.status(201).body(new ResponseDTO("Neighborhood created successfully", createdNeighborhood, 201));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteNeighborhood(@PathVariable Long id) {
        neighborhoodUseCase.deleteNeighborhood(id);
        return ResponseEntity.ok(new ResponseDTO("Neighborhood deleted successfully", null, 200));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        NeighborhoodDTO updatedNeighborhood = neighborhoodUseCase.updateNeighborhood(neighborhoodDTO);
        return ResponseEntity.ok(new ResponseDTO("Neighborhood updated successfully", updatedNeighborhood, 200));
    }
}
