package com.gtu.route_management_service.application.mapper;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.domain.model.Route;

import lombok.experimental.UtilityClass;


@UtilityClass
public class RouteMapper {
    public Route toDomain(RouteDTO routeDTO){
        return new Route(
            routeDTO.getName(),
            routeDTO.getDescription(),
            routeDTO.getStartTime(),
            routeDTO.getEndTime(),
            routeDTO.getNeighborhoodIds(),
            routeDTO.getStops()
        );
    }

    public RouteDTO toDTO(Route route){
        return new RouteDTO(
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            route.getNeighborhoodIds(),
            route.getStopsIds()
        );
    }
}
