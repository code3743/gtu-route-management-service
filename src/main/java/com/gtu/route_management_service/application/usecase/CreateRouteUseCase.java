package com.gtu.route_management_service.application.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.mapper.RouteMapper;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.service.RouteService;

@Service
public class CreateRouteUseCase {

    private final RouteService routeService;
    private final RouteMapper routeMapper; 

    public CreateRouteUseCase(RouteService routeService, RouteMapper routeMapper) {
        this.routeService = routeService;
        this.routeMapper = routeMapper;
    }

    @Transactional
    public RouteDTO execute(RouteDTO routeDTO) {
        Route route = routeMapper.toDomain(routeDTO);
        Route savedRoute = routeService.saveRoute(route);

        return routeMapper.toDTO(savedRoute); 
    }
}
