package com.gtu.route_management_service.application.mapper;

import java.util.List;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.domain.model.Route;

import lombok.experimental.UtilityClass;


@UtilityClass
public class RouteMapper {
    public Route toDomain(RouteDTO routeDTO){
        return new Route(
            routeDTO.getId(),
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
            route.getId(),
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            route.getNeighborhoodIds(),
            route.getStopsIds()
        );
    }

    public static List<RouteDTO> toDTOList(List<Route> domainList) {
        return domainList == null ? List.of() : domainList.stream().map(RouteMapper::toDTO).toList();
    }

    public static List<Route> toDomainList(List<RouteDTO> dtoList) {
        return dtoList == null ? List.of() : dtoList.stream().map(RouteMapper::toDomain).toList();
    }
}
