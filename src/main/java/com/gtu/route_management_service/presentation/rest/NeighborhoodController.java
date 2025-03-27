package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.application.mapper.NeighborhoodMapper;
import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.service.NeighborhoodService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/neighborhoods")
public class NeighborhoodController {

    private final NeighborhoodService neighborhoodService;

    public NeighborhoodController(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @GetMapping
    public ResponseEntity<List<NeighborhoodDTO>> getAllNeighborhoods() {
        List<NeighborhoodDTO> neighborhoods = neighborhoodService.getAllNeighborhoods()
                .stream()
                .map(NeighborhoodMapper::toDTO)
                .toList();
        return ResponseEntity.ok(neighborhoods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeighborhoodDTO> getNeighborhoodById(@PathVariable Long id) {
        return neighborhoodService.getNeighborhoodById(id)
                .map(neighborhood -> ResponseEntity.ok(NeighborhoodMapper.toDTO(neighborhood)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NeighborhoodDTO> createNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        Neighborhood neighborhood = NeighborhoodMapper.toDomain(neighborhoodDTO);
        Neighborhood savedNeighborhood = neighborhoodService.createNeighborhood(neighborhood);
        return ResponseEntity.ok(NeighborhoodMapper.toDTO(savedNeighborhood));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNeighborhood(@PathVariable Long id) {
        neighborhoodService.deleteNeighborhood(id);
        return ResponseEntity.noContent().build();
    }
}
