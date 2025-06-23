package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.infrastructure.persistence.entities.RouteEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.RouteEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RouteRepositoryImplTest {

    @Mock
    private JpaRouteRepository jpaRepo;

    @InjectMocks
    private RouteRepositoryImpl repository;

    private final Route route = new Route(1L, "Ruta A", null, null, null, List.of(1L, 2L), List.of(10L, 20L));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldReturnSavedRoute() {
        RouteEntity entity = RouteEntityMapper.toEntity(route, List.of(), List.of());
        when(jpaRepo.save(any())).thenReturn(entity);

        Route result = repository.save(route);

        assertEquals(route.getId(), result.getId());
    }

    @Test
    void findAll_ShouldReturnListOfRoutes() {
        when(jpaRepo.findAll()).thenReturn(List.of(RouteEntityMapper.toEntity(route, List.of(), List.of())));

        List<Route> result = repository.findAll();

        assertFalse(result.isEmpty());
    }

    @Test
    void findByName_ShouldReturnRoute() {
        when(jpaRepo.findByEntityName("Ruta A"))
                .thenReturn(Optional.of(RouteEntityMapper.toEntity(route, List.of(), List.of())));

        Optional<Route> result = repository.findByName("Ruta A");

        assertTrue(result.isPresent());
        assertEquals("Ruta A", result.get().getName());
    }

    @Test
    void existsById_ShouldReturnRoute() {
        when(jpaRepo.findByIdEntity(1L))
                .thenReturn(Optional.of(RouteEntityMapper.toEntity(route, List.of(), List.of())));

        Optional<Route> result = repository.existsById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void deleteById_ShouldCallDelete() {
        doNothing().when(jpaRepo).deleteById(1L);

        repository.deleteById(1L);

        verify(jpaRepo, times(1)).deleteById(1L);
    }
}
