package com.gtu.route_management_service.application.service;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.domain.repository.StopRepository;
import com.gtu.route_management_service.domain.service.RouteService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;

    public RouteServiceImpl(RouteRepository routeRepository, StopRepository stopRepository) {
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
    }

    @Override
    public void validateRoute(Route route) {
        if (routeRepository.findByName(route.getName()).isPresent()) {
            throw new IllegalArgumentException("The route name already exists: " + route.getName());
        }

        List<Long> stopIds = route.getStopsIds();
        List<Long> existingStopIds = stopRepository.findAllExistingIds(stopIds);
        if (existingStopIds.size() != stopIds.size()) {
            throw new IllegalArgumentException("Some stops do not exists: " + stopIds);
        }
    }

    @Override
    public Route saveRoute(Route route) {
        validateRoute(route);
        List<Long> stopIds = route.getStopsIds();
        List<Stop> stops = stopRepository.findAllById(stopIds);
        if (stops.size() != stopIds.size()) {
            throw new IllegalArgumentException("Some stops do not exist: " + stopIds);
        }
        return routeRepository.save(route, stops);
    }
    
}
