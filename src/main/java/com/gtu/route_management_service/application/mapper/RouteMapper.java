package com.gtu.route_management_service.application.mapper;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.domain.model.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {
    public Route toDomain(RouteDTO routeDTO){
        return new Route(
            routeDTO.getName(),
            routeDTO.getDescription(),
            routeDTO.getStartTime(),
            routeDTO.getEndTime(),
            NeighborhoodMapper.toDomainList(routeDTO.getNeighborhoods()),
            StopMapper.toDomainList(routeDTO.getStops())
        );
    }

    public RouteDTO toDTO(Route route){
        return new RouteDTO(
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            NeighborhoodMapper.toDTOList(route.getNeighborhood()),
            StopMapper.toDTOList(route.getStop())
        );
    }
}
