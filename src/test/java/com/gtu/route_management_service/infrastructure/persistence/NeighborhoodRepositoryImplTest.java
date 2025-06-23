package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.NeighborhoodMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NeighborhoodRepositoryImplTest {

    @Mock
    private JpaNeighborhoodRepository jpaRepo;

    @InjectMocks
    private NeighborhoodRepositoryImpl repository;

    private final Neighborhood neighborhood = new Neighborhood(1L, "Centro");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedNeighborhood() {
        NeighborhoodEntity entity = NeighborhoodMapper.toEntity(neighborhood);
        when(jpaRepo.save(any())).thenReturn(entity);

        Neighborhood result = repository.save(neighborhood);

        assertEquals(neighborhood.getId(), result.getId());
    }

    @Test
    void findAll_ShouldReturnListOfNeighborhoods() {
        when(jpaRepo.findAll()).thenReturn(List.of(NeighborhoodMapper.toEntity(neighborhood)));

        List<Neighborhood> result = repository.findAll();

        assertFalse(result.isEmpty());
    }

    @Test
    void findById_ShouldReturnNeighborhood() {
        when(jpaRepo.findById(1L)).thenReturn(Optional.of(NeighborhoodMapper.toEntity(neighborhood)));

        Optional<Neighborhood> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void existsById_ShouldReturnTrue() {
        when(jpaRepo.existsById(1L)).thenReturn(true);

        assertTrue(repository.existsById(1L));
    }

    @Test
    void deleteById_ShouldCallDelete() {
        doNothing().when(jpaRepo).deleteById(1L);
        repository.deleteById(1L);
        verify(jpaRepo, times(1)).deleteById(1L);
    }
}
