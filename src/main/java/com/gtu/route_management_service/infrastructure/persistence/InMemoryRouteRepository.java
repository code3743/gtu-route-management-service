package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRouteRepository implements RouteRepository {
    private final Map<String, Route> routes = new HashMap<>();

    @Override
    public Optional<Route> findByName(String name) {
        return Optional.ofNullable(routes.get(name));
    }

    public void save(Route route) {
        routes.put(route.getName(), route);
    }
    
}
