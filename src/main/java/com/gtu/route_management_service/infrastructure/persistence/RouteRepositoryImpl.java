package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.RouteEntityMapper;
import com.gtu.route_management_service.infrastructure.persistence.mappers.StopMapper;
import com.gtu.route_management_service.infrastructure.persistence.mappers.NeighborhoodMapper;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class RouteRepositoryImpl implements RouteRepository{

    private final JpaRouteRepository jpaRouteRepository;
    private final RouteEntityMapper routeEntityMapper;

    public RouteRepositoryImpl(JpaRouteRepository jpaRouteRepository, RouteEntityMapper routeEntityMapper) {
        this.jpaRouteRepository = jpaRouteRepository;
        this.routeEntityMapper = routeEntityMapper;
    }

    @Override
    public Route save(Route route, List<Neighborhood> neighborhoods,List<Stop> stops) {
        List<NeighborhoodEntity> neighborhoodEntities = NeighborhoodMapper.toEntityList(neighborhoods);
        List<StopEntity> stopEntities = StopMapper.toEntityList(stops);
        RouteEntity routeEntity = routeEntityMapper.toEntity(route, neighborhoodEntities, stopEntities);
        RouteEntity savedEntity = jpaRouteRepository.save(routeEntity);
        return routeEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Route save(Route route) {
        List<NeighborhoodEntity> neighborhoodEntities = jpaRouteRepository.findByNeighborhoodIds(route.getNeighborhoodIds());
        if (neighborhoodEntities.size() != route.getNeighborhoodIds().size()) {
            throw new IllegalArgumentException("Some neighborhoods could not be found: " + route.getNeighborhoodIds());
        }

        List<StopEntity> stopEntities = route.getStopsIds().stream()
            .map(id -> {
                StopEntity stop = new StopEntity();
                stop.setId(id);
                return stop;
            })
            .toList();

        RouteEntity routeEntity = routeEntityMapper.toEntity(route, neighborhoodEntities, stopEntities);
        RouteEntity savedEntity = jpaRouteRepository.save(routeEntity);
        return routeEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Route> findByName(String name) {
        return jpaRouteRepository.findByEntityName(name)
            .map(routeEntityMapper::toDomain);
    }
}
