package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteEntityMapperTest {

    @Test
    void toEntity_ShouldMapCorrectly() {
        Route route = new Route(1L, "Ruta A", "Desc", LocalTime.of(8, 0), LocalTime.of(10, 0), List.of(1L), List.of(2L));

        NeighborhoodEntity neighborhood = new NeighborhoodEntity(1L);
        StopEntity stop = new StopEntity(2L);

        RouteEntity entity = RouteEntityMapper.toEntity(route, List.of(neighborhood), List.of(stop));

        assertNotNull(entity);
        assertEquals("Ruta A", entity.getName());
        assertEquals(1, entity.getNeighborhood().size());
        assertEquals(2L, entity.getStop().get(0).getId());
    }

    @Test
    void toDomain_ShouldMapCorrectly() {
        NeighborhoodEntity neighborhood = new NeighborhoodEntity(1L, "Centro");
        StopEntity stop = new StopEntity(2L, "Parada", "Desc", 1L, 10.0, 20.0);

        RouteEntity entity = new RouteEntity(
                1L,
                "Ruta B",
                "Descripcion",
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                List.of(neighborhood),
                List.of(stop)
        );

        Route result = RouteEntityMapper.toDomain(entity);

        assertNotNull(result);
        assertEquals("Ruta B", result.getName());
        assertEquals(1, result.getNeighborhoodIds().size());
        assertEquals(2L, result.getStopsIds().get(0));
    }
}
