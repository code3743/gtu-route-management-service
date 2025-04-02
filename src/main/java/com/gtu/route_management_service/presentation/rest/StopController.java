package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.ResponseDTO;
import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.usecase.StopUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
@Tag(name = "Stops", description = "Endpoints for managing stops")
@CrossOrigin(origins = "*")
public class StopController {
    private final StopUseCase stopUseCase;

    public StopController(StopUseCase stopUseCase) {
        this.stopUseCase = stopUseCase;
    }
   
    @PostMapping
    @Operation(summary = "Create a new stop", description = "Add a new stop to the system.")
    public ResponseEntity<ResponseDTO<StopDTO>> createStop(@RequestBody StopDTO stopDTO) {
        StopDTO createdStop = stopUseCase.createStop(stopDTO);
        return ResponseEntity.status(201).body(new ResponseDTO<>("Stop created successfully", createdStop, 201));
    }

    @GetMapping
    @Operation(summary = "Get all stops", description = "Retrieve a list of all stops. Optionally, you can search by name.")
    public ResponseEntity<ResponseDTO<List<StopDTO>>> getAllStops(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            List<StopDTO> stops = stopUseCase.searchByName(search);
            return ResponseEntity.status(200).body(new ResponseDTO<>("Stops retrieved successfully", stops, 200));
        }
        List<StopDTO> stops = stopUseCase.getAllStops();    
        return ResponseEntity.status(200).body(new ResponseDTO<>("Stops retrieved successfully", stops, 200));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get stop by ID", description = "Retrieve a stop by its unique identifier.")
    public ResponseEntity<ResponseDTO<StopDTO>> getStopById(@PathVariable Long id) {
        StopDTO stopDTO = stopUseCase.getStopById(id);
        if (stopDTO == null) {
            return ResponseEntity.status(404).body(new ResponseDTO<>("Stop not found", null, 404));
        }
        return ResponseEntity.status(200).body(new ResponseDTO<>("Stop retrieved successfully", stopDTO, 200));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a stop", description = "Remove a stop from the system by its unique identifier.")
    public  ResponseEntity<ResponseDTO<Void>> deleteStop(@PathVariable Long id) {
        stopUseCase.deleteStop(id);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Stop deleted successfully", null, 200));
    }

    @PutMapping
    @Operation(summary = "Update a stop", description = "Modify the details of an existing stop.")
    public ResponseEntity<ResponseDTO<StopDTO>> updateStop(@RequestBody StopDTO stopDTO) {
        StopDTO updatedStop = stopUseCase.updateStop(stopDTO);
        return ResponseEntity.status(200).body(new ResponseDTO<>("Stop updated successfully", updatedStop, 200));
    }
}
