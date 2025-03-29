package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.ResponseDTO;
import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.usecase.StopUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
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
    public ResponseEntity<ResponseDTO> getAllStops(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            List<StopDTO> stops = stopUseCase.searchByName(search);
            return ResponseEntity.ok(new ResponseDTO("Stops retrieved successfully", stops, 200));
        }
        List<StopDTO> stops = stopUseCase.getAllStops();    
        return ResponseEntity.ok(new ResponseDTO("Stops retrieved successfully", stops, 200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getStopById(@PathVariable Long id) {
        StopDTO stopDTO = stopUseCase.getStopById(id);
        if (stopDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ResponseDTO("Stop retrieved successfully", stopDTO, 200));
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
}
