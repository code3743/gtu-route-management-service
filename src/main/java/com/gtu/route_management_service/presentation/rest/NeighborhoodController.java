package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.application.dto.ResponseDTO;
import com.gtu.route_management_service.application.usecase.NeighborhoodUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neighborhoods")
@Tag(name = "Neighborhoods", description = "Endpoints for managing neighborhoods")
@CrossOrigin(origins = "*")
public class NeighborhoodController {

    private final NeighborhoodUseCase neighborhoodUseCase;

    public NeighborhoodController(NeighborhoodUseCase neighborhoodUseCase) {
        this.neighborhoodUseCase = neighborhoodUseCase;
    }

    @GetMapping
    @Operation(summary = "Get all neighborhoods", description = "Retrieve a list of all neighborhoods. Optionally, you can search by name.")
    public ResponseEntity<ResponseDTO< List<NeighborhoodDTO>>> getAllNeighborhoods(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            List<NeighborhoodDTO> neighborhoods = neighborhoodUseCase.searchByName(search);
            return ResponseEntity.status(200).body(new ResponseDTO<>("Neighborhoods retrieved successfully", neighborhoods, 200));
        }
        List<NeighborhoodDTO> neighborhoods = neighborhoodUseCase.getAllNeighborhoods();
        return ResponseEntity.status(200).body(new ResponseDTO<>("Neighborhoods retrieved successfully", neighborhoods, 200));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get neighborhood by ID", description = "Retrieve a neighborhood by its unique identifier.")
    public ResponseEntity<ResponseDTO<NeighborhoodDTO>> getNeighborhoodById(@PathVariable Long id) {
        NeighborhoodDTO neighborhoodDTO = neighborhoodUseCase.getNeighborhoodById(id);
        if (neighborhoodDTO == null) {
            return ResponseEntity.status(404).body(new ResponseDTO<>("Neighborhood not found", null, 404));
        }
        return ResponseEntity.status(200).body(new ResponseDTO<>("Neighborhood retrieved successfully", neighborhoodDTO, 200));
    }

    @PostMapping
    @Operation(summary = "Create a new neighborhood", description = "Add a new neighborhood to the system.")
    public ResponseEntity<ResponseDTO<NeighborhoodDTO>> createNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        NeighborhoodDTO createdNeighborhood = neighborhoodUseCase.createNeighborhood(neighborhoodDTO);
        return ResponseEntity.status(201).body(new ResponseDTO<>("Neighborhood created successfully", createdNeighborhood, 201));
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a neighborhood", description = "Remove a neighborhood from the system by its unique identifier.")
    public ResponseEntity<ResponseDTO<Long>> deleteNeighborhood(@PathVariable Long id) {
        neighborhoodUseCase.deleteNeighborhood(id);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Neighborhood deleted successfully", id, 200));
    }

    @PutMapping
    @Operation(summary = "Update a neighborhood", description = "Modify an existing neighborhood in the system.")
    public ResponseEntity<ResponseDTO<NeighborhoodDTO>> updateNeighborhood(@RequestBody NeighborhoodDTO neighborhoodDTO) {
        NeighborhoodDTO updatedNeighborhood = neighborhoodUseCase.updateNeighborhood(neighborhoodDTO);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Neighborhood updated successfully", updatedNeighborhood, 200));
    }
}
