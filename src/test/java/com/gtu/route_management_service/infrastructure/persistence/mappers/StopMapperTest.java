package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StopMapperTest {

    @Test
    void toDomain_ShouldMapCorrectly() {
        StopEntity entity = new StopEntity(1L, "Parada A", "Descripción", 10L, 12.34, 56.78);
        Stop result = StopMapper.toDomain(entity);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Parada A", result.getName());
    }

    @Test
    void toDomain_ShouldReturnNull_WhenInputNull() {
        assertNull(StopMapper.toDomain(null));
    }

    @Test
    void toEntity_ShouldMapCorrectly() {
        Stop domain = new Stop(2L, "Parada B", "Otra descripción", 11L, 98.76, 54.32);
        StopEntity result = StopMapper.toEntity(domain);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals(11L, result.getNeighborhoodId());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenInputNull() {
        assertNull(StopMapper.toEntity(null));
    }

    @Test
    void toDomainList_ShouldMapListCorrectly() {
        List<StopEntity> entities = List.of(
                new StopEntity(1L, "A", "Desc A", 5L, 10.0, 20.0),
                new StopEntity(2L, "B", "Desc B", 6L, 30.0, 40.0)
        );

        List<Stop> result = StopMapper.toDomainList(entities);

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());
    }

    @Test
    void toEntityList_ShouldMapListCorrectly() {
        List<Stop> domains = List.of(
                new Stop(1L, "X", "Desc X", 1L, 10.0, 20.0),
                new Stop(2L, "Y", "Desc Y", 2L, 30.0, 40.0)
        );

        List<StopEntity> result = StopMapper.toEntityList(domains);

        assertEquals(2, result.size());
        assertEquals("Y", result.get(1).getName());
    }

    @Test
    void toDomainList_ShouldReturnEmpty_WhenNull() {
        assertTrue(StopMapper.toDomainList(null).isEmpty());
    }

    @Test
    void toEntityList_ShouldReturnEmpty_WhenNull() {
        assertTrue(StopMapper.toEntityList(null).isEmpty());
    }
}
