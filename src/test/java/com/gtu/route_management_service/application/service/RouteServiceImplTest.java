package com.gtu.route_management_service.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import com.gtu.route_management_service.domain.repository.RouteRepository;
import com.gtu.route_management_service.domain.repository.StopRepository;

@ExtendWith(MockitoExtension.class)
class RouteServiceImplTest {
    @Mock
    private RouteRepository routeRepository;

    @Mock
    private StopRepository stopRepository;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    private Route route;

    @BeforeEach
    void setUp() {
        route = new Route();
        route.setId(1L);
        route.setName("Test Route");
        route.setDescription("Test Description");
        route.setStartTime(LocalTime.parse("08:00"));
        route.setEndTime(LocalTime.parse("09:00"));
        route.setNeighborhoodIds(Arrays.asList(1L, 2L));
        route.setStop(Arrays.asList(1L, 2L));
    }

    @Test
    void validateRoute_Success() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(stopRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L, 2L));
        when(neighborhoodRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L, 2L));

        assertDoesNotThrow(() -> routeService.validateRoute(route));

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
    }

    @Test
    void validateRoute_NameAlreadyExists_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.of(route));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.validateRoute(route);
        });
        assertEquals("The route name already exists: Test Route", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, never()).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, never()).findAllExistingIds(Arrays.asList(1L, 2L));
    }

    @Test
    void validateRoute_StopsDoNotExist_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(stopRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.validateRoute(route);
        });
        assertEquals("Some stops do not exist: [1, 2]", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, never()).findAllExistingIds(Arrays.asList(1L, 2L));
    }

    @Test
    void validateRoute_NeighborhoodsDoNotExist_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(stopRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L, 2L));
        when(neighborhoodRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.validateRoute(route);
        });
        assertEquals("Some neighborhoods do not exist: [1, 2]", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
    }

    @Test
    void saveRoute_Success() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(stopRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L, 2L));
        when(neighborhoodRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L, 2L));
        when(routeRepository.save(route)).thenReturn(route);

        Route result = routeService.saveRoute(route);
        assertNotNull(result);
        assertEquals("Test Route", result.getName());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void saveRoute_NameAlreadyExists_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.of(route));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.saveRoute(route);
        });
        assertEquals("The route name already exists: Test Route", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, never()).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, never()).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(routeRepository, never()).save(any());
    }

    @Test
    void saveRoute_StopsDoNotExist_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(stopRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.saveRoute(route);
        });
        assertEquals("Some stops do not exist: [1, 2]", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, never()).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(routeRepository, never()).save(any(Route.class));
    }

    @Test
    void saveRoute_NeighborhoodsDoNotExist_ThrowException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(stopRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L, 2L));
        when(neighborhoodRepository.findAllExistingIds(Arrays.asList(1L, 2L))).thenReturn(Arrays.asList(1L));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.saveRoute(route);
        });
        assertEquals("Some neighborhoods do not exist: [1, 2]", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(stopRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(neighborhoodRepository, times(1)).findAllExistingIds(Arrays.asList(1L, 2L));
        verify(routeRepository, never()).save(any(Route.class));
    }

    @Test
    void getAllRoutes_Success() {
        List<Route> routes = Arrays.asList(route);
        when(routeRepository.findAll()).thenReturn(routes);
        List<Route> result = routeService.getAllRoutes();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Route", result.get(0).getName());
        verify(routeRepository, times(1)).findAll();
    }

    @Test
    void getAllRoutes_EmptyList() {
        when(routeRepository.findAll()).thenReturn(Collections.emptyList());
        List<Route> result = routeService.getAllRoutes();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(routeRepository, times(1)).findAll();
    }

    @Test
    void createRoute_Success() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());
        when(routeRepository.save(route)).thenReturn(route);

        Route createdRoute = routeService.createRoute(route);

        assertNotNull(createdRoute);
        assertEquals("Test Route", createdRoute.getName());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(routeRepository, times(1)).save(route);
    }

    @Test
    void createRoute_NameAlreadyExists_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.of(route));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.createRoute(route);
        });
        assertEquals("The route name already exists: Test Route", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
        verify(routeRepository, never()).save(any());
    }

    @Test
    void updateRoute_Success() {
        Route updatedRoute = new Route();
        updatedRoute.setId(1L);
        updatedRoute.setName("Updated Route");
        updatedRoute.setDescription("Updated Description");
        updatedRoute.setStartTime(LocalTime.parse("09:00"));
        updatedRoute.setEndTime(LocalTime.parse("15:00"));
        updatedRoute.setNeighborhoodIds(Arrays.asList(1L, 2L));
        updatedRoute.setStop(Arrays.asList(1L, 2L));

        when(routeRepository.existsById(1L)).thenReturn(Optional.of(route));
        when(routeRepository.save(updatedRoute)).thenReturn(updatedRoute);

        Route result = routeService.updateRoute(updatedRoute);
        assertNotNull(result);
        assertEquals("Updated Route", result.getName());

        verify(routeRepository, times(1)).existsById(1L);
        verify(routeRepository, times(1)).save(updatedRoute);
    }

    @Test
    void updateRoute_RouteDoesNotExist_ThrowsException() {
        when(routeRepository.existsById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.updateRoute(route);
        });
        assertEquals("Route does not exist", exception.getMessage());

        verify(routeRepository, times(1)).existsById(1L);
        verify(routeRepository, never()).save(any(Route.class));
    }

    @Test
    void updateRoute_NameAlreadyExists_ThrowsException() {
        Route anotherRoute = new Route();
        anotherRoute.setId(2L);
        anotherRoute.setName("Test Route");
        when(routeRepository.existsById(1L)).thenReturn(Optional.of(anotherRoute));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.updateRoute(route);
        });
        assertEquals("The route name already exists: Test Route", exception.getMessage());

        verify(routeRepository, times(1)).existsById(1L);
        verify(routeRepository, never()).save(any(Route.class));
    }

    @Test
    void deleteRoute_Success() {
        when(routeRepository.existsById(1L)).thenReturn(Optional.of(route));
        routeService.deleteRoute(1L);

        verify(routeRepository, times(1)).existsById(1L);
        verify(routeRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteRoute_RouteDoesNotExist_ThrowsException() {
        when(routeRepository.existsById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.deleteRoute(1L);
        });
        assertEquals("Route does not exist", exception.getMessage());
        verify(routeRepository, times(1)).existsById(1L);
        verify(routeRepository, never()).deleteById(anyLong());
    }

    @Test
    void getRouteByName_Success() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.of(route));
        
        List<Route> result = routeService.getRouteByName("Test Route");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Route", result.get(0).getName());
        
        verify(routeRepository, times(1)).findByName("Test Route");
    }

    @Test
    void getRouteByName_NotFound_ThrowsException() {
        when(routeRepository.findByName("Test Route")).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.getRouteByName("Test Route");
        });
        assertEquals("No routes found with the name: Test Route", exception.getMessage());

        verify(routeRepository, times(1)).findByName("Test Route");
    }

    @Test
    void getRouteById_Success() {
        when(routeRepository.existsById(1L)).thenReturn(Optional.of(route));

        Route result = routeService.getRouteById(1L);
        assertNotNull(result);
        assertEquals("Test Route", result.getName());

        verify(routeRepository, times(1 )).existsById(1L);
    }

    @Test
    void getRouteById_NotFound_ThrowsException() {
        when(routeRepository.existsById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeService.getRouteById(1L);
        });
        assertEquals("Route does not exist", exception.getMessage());

        verify(routeRepository, times(1)).existsById(1L);
    }  
}
