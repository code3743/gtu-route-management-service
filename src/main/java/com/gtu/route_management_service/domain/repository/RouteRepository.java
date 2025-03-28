package com.gtu.route_management_service.domain.repository;

import com.gtu.route_management_service.domain.model.Route;


import java.util.Optional;

public interface RouteRepository {
    Optional<Route> findByName(String nombre);
    Route save(Route route);

}
