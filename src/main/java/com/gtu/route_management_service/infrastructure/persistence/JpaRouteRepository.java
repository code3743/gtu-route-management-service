package com.gtu.route_management_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;

import java.util.Optional;

@Repository
public interface JpaRouteRepository extends JpaRepository<RouteEntity, Long> {
    Optional<RouteEntity> findByName(String nombre);
}