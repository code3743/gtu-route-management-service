package com.gtu.route_management_service.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.mapper.RouteMapper;
import com.gtu.route_management_service.domain.service.RouteService;

@Service
public class RouteUseCase {

    private final RouteService routeService;

    public RouteUseCase(RouteService routeService) {
        this.routeService = routeService;
    }

    public RouteDTO createRoute(RouteDTO routeDTO) {
        return RouteMapper.toDTO(routeService.createRoute(RouteMapper.toDomain(routeDTO)));
    }

    public List<RouteDTO> getAllRoutes() {
        return RouteMapper.toDTOList(routeService.getAllRoutes());
    }

    public RouteDTO updateRoute(RouteDTO routeDTO) {
        return RouteMapper.toDTO(routeService.updateRoute(RouteMapper.toDomain(routeDTO)));
    }

    public void deleteRoute(Long id) {
        routeService.deleteRoute(id);
    }
}
