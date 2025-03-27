package com.gtu.route_management_service.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStopRepository extends JpaRepository<StopEntity, Long> {
}