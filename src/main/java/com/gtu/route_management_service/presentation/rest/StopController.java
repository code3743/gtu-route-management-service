package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.usecase.StopUseCase;

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
    public ResponseEntity<StopDTO> createStop(@RequestBody StopDTO stopDTO) {
        StopDTO createdStop = stopUseCase.createStop(stopDTO);
        return ResponseEntity.status(201).body(createdStop);
    }

    @GetMapping
    public ResponseEntity<List<StopDTO>> getAllStops(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            List<StopDTO> stops = stopUseCase.searchByName(search);
            return ResponseEntity.ok(stops);
        }
        List<StopDTO> stops = stopUseCase.getAllStops();    
        return ResponseEntity.ok(stops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StopDTO> getStopById(@PathVariable Long id) {
        StopDTO stopDTO = stopUseCase.getStopById(id);
        if (stopDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(stopDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteStop(@PathVariable Long id) {
        stopUseCase.deleteStop(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<StopDTO> updateStop(@RequestBody StopDTO stopDTO) {
        StopDTO updatedStop = stopUseCase.updateStop(stopDTO);
        return ResponseEntity.ok(updatedStop);
    }
}
