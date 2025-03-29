package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.usecase.StopUseCase;
import com.gtu.route_management_service.application.dto.ResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
public class StopController {
    private final StopUseCase stopUseCase;

    public StopController(StopUseCase stopUseCase) {
        this.stopUseCase = stopUseCase;
    }
   
    @PostMapping
    public ResponseEntity<ResponseDTO> createStop(@RequestBody StopDTO stopDTO) {
        StopDTO createdStop = stopUseCase.createStop(stopDTO);
        return ResponseEntity.status(201).body(new ResponseDTO("Stop created successfully", createdStop, 201));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllStops() {
        List<StopDTO> stops = stopUseCase.getAllStops();
        return ResponseEntity.ok(new ResponseDTO("Stops retrieved successfully", stops, 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getStopById(@PathVariable Long id) {
        StopDTO stop = stopUseCase.getStopById(id);
        return ResponseEntity.ok(new ResponseDTO("Stop retrieved successfully", stop, 200));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ResponseDTO> deleteStop(@PathVariable Long id) {
        stopUseCase.deleteStop(id);
        return ResponseEntity.ok(new ResponseDTO("Stop deleted successfully", null, 200));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateStop(@RequestBody StopDTO stopDTO) {
        StopDTO updatedStop = stopUseCase.updateStop(stopDTO);
        return ResponseEntity.ok(new ResponseDTO("Stop updated successfully", updatedStop, 200));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> searchByName(@RequestParam String name) {
        List<StopDTO> stops = stopUseCase.searchByName(name);
        return ResponseEntity.ok(new ResponseDTO("Stops retrieved successfully", stops, 200));
    }
}
