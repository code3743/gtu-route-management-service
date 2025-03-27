package com.gtu.route_management_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;


@Repository
public interface JpaNeighborhoodRepository extends JpaRepository<NeighborhoodEntity, Long> {
}