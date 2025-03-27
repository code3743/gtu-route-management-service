package com.gtu.route_management_service.application.usecase;

import org.springframework.transaction.annotation.Transactional;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.service.RouteService;

public class CreateRouteUseCase {
    private final RouteService routeService;

    public CreateRouteUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    @Transactional
    public RouteDTO execute(RouteDTO routeDTO) {
        Route route = new Route(
                routeDTO.getName(),
                routeDTO.getStartTime(),
                routeDTO.getEndTime(),
                routeDTO.getNeighborhoods(),
                routeDTO.getStopIds());

        routeService.validateRoute(route);
        Route savedRoute = routeService.saveRoute(route);

        return toDTO(savedRoute);
    }

    private RouteDTO toDTO(Route route) {
        return new RouteDTO(
                route.getName(),
                route.getDescription(),
                route.getStartTime(),
                route.getEndTime(),
                route.getNeighborhoods(),
                route.getStopIds());
    }
}
