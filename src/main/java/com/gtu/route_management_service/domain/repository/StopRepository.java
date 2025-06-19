package com.gtu.route_management_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gtu.route_management_service.domain.model.Stop;

public interface StopRepository {
    boolean existsById(Long id);
    List<Long> findAllExistingIds(List<Long> ids);
    Optional<Stop> findById(Long id);
    Stop save(Stop stop);
    void deleteById(Long id);
    List<Stop> findAll();
    List<Stop> findAllById(List<Long> ids);
    Stop update(Stop stop);
    List<Stop> searchByName(String name);
}