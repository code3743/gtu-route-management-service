package com.gtu.route_management_service.presentation.rest;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.mapper.StopMapper;
import com.gtu.route_management_service.domain.service.StopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
public class StopController {
    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }
   
    @PostMapping
    public StopDTO createStop(@RequestBody StopDTO stopDTO) {
        return StopMapper.toDTO(stopService.createStop(StopMapper.toDomain(stopDTO)));
    }

    @GetMapping
    public List<StopDTO> getAllStops() {
        return StopMapper.toDTOList(stopService.getAllStops());
    }

    @GetMapping("/{id}")
    public StopDTO getStopById(@PathVariable Long id) {
        return stopService.getStopById(id)
            .map(StopMapper::toDTO)
            .orElseThrow(() -> new RuntimeException("Stop not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteStop(@PathVariable Long id) {
        stopService.deleteStop(id);
    }
}
