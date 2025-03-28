package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.RouteEntityMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RouteRepositoryImpl implements RouteRepository{

    private final JpaRouteRepository jpaRouteRepository;

    public RouteRepositoryImpl(JpaRouteRepository jpaRouteRepository) {
        this.jpaRouteRepository = jpaRouteRepository;
    }

    @Override
    public Route save(Route route) {
        List<NeighborhoodEntity> neighborhoodEntities = route.getNeighborhoodIds().stream()
            .map(NeighborhoodEntity::new)
            .toList();
       
        List<StopEntity> stopEntities = route.getStopsIds().stream()
            .map(StopEntity::new)
            .toList();

        RouteEntity routeEntity = RouteEntityMapper.toEntity(route, neighborhoodEntities, stopEntities);
        RouteEntity savedEntity = jpaRouteRepository.save(routeEntity);
        return RouteEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Route> findByName(String name) {
        return jpaRouteRepository.findByEntityName(name)
            .map(RouteEntityMapper::toDomain);
    }
}
