package com.gtu.route_management_service.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.application.mapper.StopMapper;
import com.gtu.route_management_service.domain.service.StopService;

@Service
public class StopUseCase {

    private final StopService stopService;

    public StopUseCase(StopService stopService) {
        this.stopService = stopService;
    }
    
    public StopDTO createStop(StopDTO stopDTO) {
        return StopMapper.toDTO(stopService.createStop(StopMapper.toDomain(stopDTO)));
    }

    public StopDTO getStopById(Long id) {
        return StopMapper.toDTO(stopService.getStopById(id).orElse(null));
    }

    public void deleteStop(Long id) {
        stopService.deleteStop(id);
    }
    public List<StopDTO> getAllStops() {
        return StopMapper.toDTOList(stopService.getAllStops());
    }

    public StopDTO updateStop(StopDTO stopDTO) {
        return StopMapper.toDTO(stopService.updateStop(StopMapper.toDomain(stopDTO)));
    }
}
