package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;

import org.springframework.stereotype.Component;

@Component
public class RouteEntityMapper {
    public RouteEntity toEntity(Route route) {
        return new RouteEntity(
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            NeighborhoodMapper.toEntityList(route.getNeighborhood()),
            StopMapper.toEntityList(route.getStop())
        );
    }

    public Route toDomain(RouteEntity entity) {
        return new Route(
            entity.getName(),
            entity.getDescription(),
            entity.getStarTime(),
            entity.getEndTime(),
            NeighborhoodMapper.toDomainList(entity.getNeighborhood()),
            StopMapper.toDomainList(entity.getStop())
        );
    }
}
