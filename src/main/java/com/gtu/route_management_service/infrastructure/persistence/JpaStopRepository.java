package com.gtu.route_management_service.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;

@Repository
public interface JpaStopRepository extends JpaRepository<StopEntity, Long> {
    @Query("SELECT n FROM StopEntity n WHERE n.name ILIKE %:name%")
    List<StopEntity> searchByName(@Param("name") String name);
}
