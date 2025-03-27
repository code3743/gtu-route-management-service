package com.gtu.route_management_service.infrastructure.persistence;


import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.RouteEntityMapper;
import com.gtu.route_management_service.infrastructure.persistence.mappers.StopMapper;

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
    public Route save(Route route, List<Stop> stops) {
        RouteEntity routeEntity = routeEntityMapper.toEntity(route, StopMapper.toEntityList(stops));
        RouteEntity savedEntity = jpaRouteRepository.save(routeEntity);
        return routeEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Route> findByName(String name) {
        return jpaRouteRepository.findByName(name)
        .map(routeEntityMapper::toDomain);
    }
}
