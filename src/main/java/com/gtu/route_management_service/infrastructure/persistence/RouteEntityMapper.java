package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Route;
import org.springframework.stereotype.Component;

@Component
public class RouteEntityMapper {
    public RouteEntity toEntity(Route route) {
        return new RouteEntity(
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            route.getNeighborhoods(),
            route.getStopIds()
        );
    }

    public Route toDomain(RouteEntity entity) {
        return new Route(
            entity.getName(),
            entity.getStarTime(),
            entity.getEndTime(),
            entity.getNeighborhoods(),
            entity.getStopIds()
        );
    }
}
