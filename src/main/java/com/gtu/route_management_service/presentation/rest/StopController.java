package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.usecase.StopUseCase;
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
    public StopDTO createStop(@RequestBody StopDTO stopDTO) {
        return stopUseCase.createStop(stopDTO);
    }

    @GetMapping
    public List<StopDTO> getAllStops() {
        return stopUseCase.getAllStops();
    }

    @GetMapping("/{id}")
    public StopDTO getStopById(@PathVariable Long id) {
        return stopUseCase.getStopById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteStop(@PathVariable Long id) {
    stopUseCase.deleteStop(id);
    }

    @PutMapping
    public StopDTO updateStop(@RequestBody StopDTO stopDTO) {
        return stopUseCase.updateStop(stopDTO);
    }
}
