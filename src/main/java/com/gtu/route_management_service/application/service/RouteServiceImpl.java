package com.gtu.route_management_service.application.service;

import com.gtu.route_management_service.domain.model.Route;
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

        List<Long> stopIds = route.getStopsIds();
        List<Long> existingStopIds = stopRepository.findAllExistingIds(stopIds);
        if (existingStopIds.size() != stopIds.size()) {
            throw new IllegalArgumentException("Some stops do not exist: " + stopIds);
        }

        List<Long> neighborhoodIds = route.getNeighborhoodIds();
        List<Long> existingNeighborhoodIds = neighborhoodRepository.findAllExistingIds(neighborhoodIds);
        if (existingNeighborhoodIds.size() != neighborhoodIds.size()) {
            throw new IllegalArgumentException("Some neighborhoods do not exist: " + neighborhoodIds);
        }

    }

    @Override
    public Route saveRoute(Route route) {
        validateRoute(route);
        return routeRepository.save(route);
    }

    @Override
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route createRoute(Route route) {
        if (routeRepository.findByName(route.getName()).isPresent()) {
            throw new IllegalArgumentException("The route name already exists: " + route.getName());
        }
        return routeRepository.save(route);
    }

    @Override
    public Route updateRoute(Route domain) {
        Route existingRoute = routeRepository.existsById(domain.getId())
                .orElseThrow(() -> new IllegalArgumentException("Route does not exist"));
        if (!existingRoute.getId().equals(domain.getId())) {
            throw new IllegalArgumentException("The route name already exists: " + domain.getName());
        }
        return routeRepository.save(domain);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
        if (!routeRepository.existsById(id).isPresent()) {
            throw new IllegalArgumentException("Route does not exist");
        }
    }
    
}
