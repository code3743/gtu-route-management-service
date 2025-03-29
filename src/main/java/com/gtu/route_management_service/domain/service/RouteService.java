package com.gtu.route_management_service.domain.service;

import java.util.List;

import com.gtu.route_management_service.domain.model.Route;

public interface RouteService {
    void validateRoute(Route route);
    Route saveRoute(Route route);
    List<Route> getAllRoutes();
    Route createRoute(Route domain);
    Route updateRoute(Route domain);
    void deleteRoute(Long id);
    List<Route> getRouteByName(String name);
    Route getRouteById(Long id);

}
