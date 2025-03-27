package com.gtu.route_management_service.application.service;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.domain.repository.StopRepository;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import com.gtu.route_management_service.domain.service.RouteService;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final NeighborhoodRepository neighborhoodRepository;

    public RouteServiceImpl(RouteRepository routeRepository, NeighborhoodRepository neighborhoodsRepository, StopRepository stopRepository) {
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
        this.neighborhoodRepository = neighborhoodsRepository;
    }

    @Override
    public void validateRoute(Route route) {
        if (routeRepository.findByName(route.getName()).isPresent()) {
            throw new IllegalArgumentException("The route name already exists: " + route.getName());
        }

    }

    @Override
    public Route saveRoute(Route route) {
        validateRoute(route);
        List<Long> neighborhoodsIds = route.getNeighborhoodsIds();
        List<Neighborhood> neighborhoods = neighborhoodRepository.findAllById(neighborhoodsIds);
        if (neighborhoods.size() != neighborhoodsIds.size()) {
            throw new IllegalArgumentException("Some neighborhoods do not exist: " + neighborhoodsIds);
        }
        System.out.println("neighborhoods: " + neighborhoods);
        List<Long> stopIds = route.getStopsIds();
        List<Stop> stops = stopRepository.findAllById(stopIds);
        if (stops.size() != stopIds.size()) {
            throw new IllegalArgumentException("Some stops do not exist: " + stopIds);
        }
        return routeRepository.save(route, neighborhoods,stops);
    }
    
}
