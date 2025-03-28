package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;

import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public class RouteEntityMapper {
    public RouteEntity toEntity(Route route, List<NeighborhoodEntity> neighborhoodsIds,List<StopEntity> stopsIds) {
        return new RouteEntity(
            route.getName(),
            route.getDescription(),
            route.getStartTime(),
            route.getEndTime(),
            neighborhoodsIds,
            stopsIds
        );
    }

    public Route toDomain(RouteEntity entity) {
        return new Route(
            entity.getName(),
            entity.getDescription(),
            entity.getStarTime(),
            entity.getEndTime(),
            entity.getNeighborhood().stream().map(NeighborhoodEntity::getId).toList(),
            entity.getStop().stream().map(StopEntity::getId).toList()
        );
    }
}
