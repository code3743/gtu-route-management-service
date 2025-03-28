package com.gtu.route_management_service.domain.service;

import com.gtu.route_management_service.domain.model.Neighborhood;

import java.util.List;
import java.util.Optional;

public interface NeighborhoodService {
    Neighborhood createNeighborhood(Neighborhood neighborhood);
    List<Neighborhood> getAllNeighborhoods();
    List<Neighborhood> getNeighborhoodsByIds(List<Long> id);
    Optional<Neighborhood> getNeighborhoodById(Long id);
    void deleteNeighborhood(Long id);
    Neighborhood updateNeighborhood(Neighborhood neighborhood);
}
