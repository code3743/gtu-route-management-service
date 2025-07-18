package com.gtu.route_management_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gtu.route_management_service.domain.model.Neighborhood;

public interface NeighborhoodRepository {
    Neighborhood save(Neighborhood neighborhood);
    boolean existsById(Long id);
    List<Neighborhood> findAll();
    Optional<Neighborhood> findById(Long id);
    List<Neighborhood> findAllById(List<Long> id);
    void deleteById(Long id);
    Neighborhood update(Neighborhood neighborhood);
    List<Long> findAllExistingIds(List<Long> neighborhoodIds);
    List<Neighborhood> searchByName(String name);
} 