package com.gtu.route_management_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRouteRepository extends JpaRepository<RouteEntity, Long> {
    @Query("SELECT r FROM RouteEntity r WHERE r.name = :name")
    Optional<RouteEntity> findByEntityName(@Param("name") String name);

    default List<RouteEntity> findAllEntities() {
        return findAll();
    }

    default Optional<RouteEntity> findByIdEntity(Long id){
        return findById(id);
    } 
}