package com.gtu.route_management_service.infrastructure.persistence;


import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.RouteEntityMapper;
import com.gtu.route_management_service.infrastructure.persistence.mappers.StopMapper;
import com.gtu.route_management_service.infrastructure.persistence.mappers.NeighborhoodMapper;

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
    public Route save(Route route, List<Neighborhood> neighborhoods,List<Stop> stops) {
        RouteEntity routeEntity = RouteEntityMapper.toEntity(route, NeighborhoodMapper.toEntityList(neighborhoods),StopMapper.toEntityList(stops));
        RouteEntity savedEntity = jpaRouteRepository.save(routeEntity);
        return RouteEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Route> findByName(String name) {
        return jpaRouteRepository.findByName(name)
        .map(RouteEntityMapper::toDomain);
    }
}
