package com.gtu.route_management_service.domain.service;

import com.gtu.route_management_service.domain.model.Stop;
import java.util.List;
import java.util.Optional;

public interface StopService {
    Stop createStop(Stop stop);
    List<Stop> getAllStops();
    Optional<Stop> getStopById(Long id);
    void deleteStop(Long id);
    Stop updateStop(Stop stop);
    List<Stop> searchByName(String name);
}
