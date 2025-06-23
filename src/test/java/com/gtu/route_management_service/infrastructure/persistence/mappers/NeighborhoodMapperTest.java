package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NeighborhoodMapperTest {

    @Test
    void toDomain_ShouldMapCorrectly() {
        NeighborhoodEntity entity = new NeighborhoodEntity(1L, "Centro");
        Neighborhood result = NeighborhoodMapper.toDomain(entity);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Centro", result.getName());
    }

    @Test
    void toDomain_ShouldReturnNull_WhenInputNull() {
        assertNull(NeighborhoodMapper.toDomain(null));
    }

    @Test
    void toEntity_ShouldMapCorrectly() {
        Neighborhood domain = new Neighborhood(2L, "Sur");
        NeighborhoodEntity result = NeighborhoodMapper.toEntity(domain);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Sur", result.getName());
    }

    @Test
    void toEntity_ShouldReturnNull_WhenInputNull() {
        assertNull(NeighborhoodMapper.toEntity(null));
    }

    @Test
    void toDomainList_ShouldMapListCorrectly() {
        List<NeighborhoodEntity> entities = List.of(
                new NeighborhoodEntity(1L, "Centro"),
                new NeighborhoodEntity(2L, "Norte")
        );

        List<Neighborhood> result = NeighborhoodMapper.toDomainList(entities);

        assertEquals(2, result.size());
        assertEquals("Centro", result.get(0).getName());
    }

    @Test
    void toEntityList_ShouldMapListCorrectly() {
        List<Neighborhood> domains = List.of(
                new Neighborhood(1L, "Sur"),
                new Neighborhood(2L, "Este")
        );

        List<NeighborhoodEntity> result = NeighborhoodMapper.toEntityList(domains);

        assertEquals(2, result.size());
        assertEquals("Este", result.get(1).getName());
    }

    @Test
    void toDomainList_ShouldReturnEmptyList_WhenNullInput() {
        List<Neighborhood> result = NeighborhoodMapper.toDomainList(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void toEntityList_ShouldReturnEmptyList_WhenNullInput() {
        List<NeighborhoodEntity> result = NeighborhoodMapper.toEntityList(null);
        assertTrue(result.isEmpty());
    }
}
