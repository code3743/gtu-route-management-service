package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.infrastructure.persistence.mappers.StopMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StopRepositoryImplTest {

    @Mock
    private JpaStopRepository jpaRepo;

    @InjectMocks
    private StopRepositoryImpl repository;

    private final Stop stop = new Stop(1L, "Parada 1", null, null, null, null);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedStop() {
        when(jpaRepo.save(any())).thenReturn(StopMapper.toEntity(stop));

        Stop result = repository.save(stop);

        assertEquals(stop.getId(), result.getId());
    }

    @Test
    void findById_ShouldReturnStop() {
        when(jpaRepo.findById(1L)).thenReturn(Optional.of(StopMapper.toEntity(stop)));

        Optional<Stop> result = repository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void findAll_ShouldReturnListOfStops() {
        when(jpaRepo.findAll()).thenReturn(List.of(StopMapper.toEntity(stop)));

        List<Stop> result = repository.findAll();

        assertFalse(result.isEmpty());
    }

    @Test
    void deleteById_ShouldCallDelete() {
        doNothing().when(jpaRepo).deleteById(1L);

        repository.deleteById(1L);

        verify(jpaRepo, times(1)).deleteById(1L);
    }

    @Test
    void existsById_ShouldReturnTrue() {
        when(jpaRepo.existsById(1L)).thenReturn(true);

        assertTrue(repository.existsById(1L));
    }
}
