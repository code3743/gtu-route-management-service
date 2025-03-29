package com.gtu.route_management_service.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.application.mapper.NeighborhoodMapper;
import com.gtu.route_management_service.domain.service.NeighborhoodService;

@Service
public class NeighborhoodUseCase {
    private final NeighborhoodService neighborhoodService;


    public NeighborhoodUseCase(NeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }


    public NeighborhoodDTO createNeighborhood(NeighborhoodDTO neighborhoodDTO) {
        return NeighborhoodMapper.toDTO(neighborhoodService.createNeighborhood(NeighborhoodMapper.toDomain(neighborhoodDTO)));
    }

    public NeighborhoodDTO getNeighborhoodById(Long id) {
        return NeighborhoodMapper.toDTO(neighborhoodService.getNeighborhoodById(id).orElse(null));
    }

    public void deleteNeighborhood(Long id) {
        neighborhoodService.deleteNeighborhood(id);
    }

    public NeighborhoodDTO updateNeighborhood(NeighborhoodDTO neighborhoodDTO) {
        return NeighborhoodMapper.toDTO(neighborhoodService.updateNeighborhood(NeighborhoodMapper.toDomain(neighborhoodDTO)));
    }

    public List<NeighborhoodDTO> getAllNeighborhoods() {
        return NeighborhoodMapper.toDTOList(neighborhoodService.getAllNeighborhoods());
    }

    public List<NeighborhoodDTO> getNeighborhoodsByIds(List<Long> ids) {
        return NeighborhoodMapper.toDTOList(neighborhoodService.getNeighborhoodsByIds(ids));
    }


}
