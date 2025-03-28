package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.application.usecase.NeighborhoodUseCase;


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
    public ResponseEntity<List<NeighborhoodDTO>> getAllNeighborhoods() {
       return ResponseEntity.ok(neighborhoodUseCase.getAllNeighborhoods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeighborhoodDTO> getNeighborhoodById(@PathVariable Long id) {
        NeighborhoodDTO neighborhoodDTO = neighborhoodUseCase.getNeighborhoodById(id);
        if (neighborhoodDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(neighborhoodDTO);
    }

    @PostMapping
    public ResponseEntity<NeighborhoodDTO> createNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        NeighborhoodDTO createdNeighborhood = neighborhoodUseCase.createNeighborhood(neighborhoodDTO);
        return ResponseEntity.status(201).body(createdNeighborhood);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNeighborhood(@PathVariable Long id) {
        neighborhoodUseCase.deleteNeighborhood(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<NeighborhoodDTO> updateNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        NeighborhoodDTO updatedNeighborhood = neighborhoodUseCase.updateNeighborhood(neighborhoodDTO);
        return ResponseEntity.ok(updatedNeighborhood);
    }
}
