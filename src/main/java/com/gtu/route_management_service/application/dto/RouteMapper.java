package com.gtu.route_management_service.application.dto;

import com.gtu.route_management_service.domain.model.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteMapper {
    public Route toDomain(RouteDTO routeDTO){
        return new Route(
            routeDTO.getName(),
            routeDTO.getStartTime(),
            routeDTO.getEndTime(),
            routeDTO.getNeighborhoods(),
            routeDTO.getStopIds()
        );
    }

    public RouteDTO toDTO(Route route){
        return new RouteDTO(
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            route.getNeighborhoods(),
            route.getStopIds()
        );
    }
}
