package com.gtu.route_management_service.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.domain.model.Route;
import com.gtu.route_management_service.domain.service.RouteService;

@ExtendWith(MockitoExtension.class)
class RouteUseCaseTest {
    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteUseCase routeUseCase;

    private Route route;
    private RouteDTO routeDTO;

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

        routeDTO = new RouteDTO();
        routeDTO.setId(1L);
        routeDTO.setName("Test Route");
        routeDTO.setDescription("Test Description");
        routeDTO.setStartTime(LocalTime.parse("08:00"));
        routeDTO.setEndTime(LocalTime.parse("09:00"));
        routeDTO.setNeighborhoodIds(Arrays.asList(1L, 2L));
        routeDTO.setStops(Arrays.asList(1L, 2L));
    }

    @Test
    void createRoute_Success() {
        when(routeService.createRoute(any(Route.class))).thenReturn(route);
        RouteDTO result = routeUseCase.createRoute(routeDTO);

        assertNotNull(result);
        assertEquals(routeDTO.getId(), result.getId());
        assertEquals(routeDTO.getName(), result.getName());
        assertEquals(routeDTO.getDescription(), result.getDescription());
        assertEquals(routeDTO.getStartTime(), result.getStartTime());
        assertEquals(routeDTO.getEndTime(), result.getEndTime());
        assertEquals(routeDTO.getNeighborhoodIds(), result.getNeighborhoodIds());
        assertEquals(routeDTO.getStops(), result.getStops());

        verify(routeService, times(1)).createRoute(any(Route.class));
    }

    @Test 
    void createRoute_RouteServiceThrowsException() {
        when(routeService.createRoute(any(Route.class))).thenThrow(new IllegalArgumentException("The route name already exists: Test Route"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeUseCase.createRoute(routeDTO);
        });
        assertEquals("The route name already exists: Test Route", exception.getMessage());

        verify(routeService, times(1)).createRoute(any(Route.class));
    }

    @Test
    void getAllRoutes_Success() {
        List<Route> routes = Arrays.asList(route);
        when(routeService.getAllRoutes()).thenReturn(routes);
        List<RouteDTO> result = routeUseCase.getAllRoutes();

        assertNotNull(result);
        assertEquals(1, result.size());
        RouteDTO resultDTO = result.get(0);
        assertEquals(routeDTO.getId(), resultDTO.getId());
        assertEquals(routeDTO.getName(), resultDTO.getName());
        assertEquals(routeDTO.getDescription(), resultDTO.getDescription());
        assertEquals(routeDTO.getStartTime(), resultDTO.getStartTime());
        assertEquals(routeDTO.getEndTime(), resultDTO.getEndTime());
        assertEquals(routeDTO.getNeighborhoodIds(), resultDTO.getNeighborhoodIds());
        assertEquals(routeDTO.getStops(), resultDTO.getStops());

        verify(routeService, times(1)).getAllRoutes();
    }

    @Test
    void getAllRoutes_EmptyList() {
        when(routeService.getAllRoutes()).thenReturn(Collections.emptyList());
        
        List<RouteDTO> result = routeUseCase.getAllRoutes();
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(routeService, times(1)).getAllRoutes();
    }

    @Test
    void updateRoute_Success(){
        Route updatedRoute = new Route();
        updatedRoute.setId(1L);
        updatedRoute.setName("Updated Route");
        updatedRoute.setDescription("Updated Description");
        updatedRoute.setStartTime(LocalTime.parse("09:00"));
        updatedRoute.setEndTime(LocalTime.parse("15:00"));
        updatedRoute.setNeighborhoodIds(Arrays.asList(1L, 2L));
        updatedRoute.setStop(Arrays.asList(1L, 2L));

        RouteDTO updatedRouteDTO = new RouteDTO();
        updatedRouteDTO.setId(1L);
        updatedRouteDTO.setName("Updated Route");
        updatedRouteDTO.setDescription("Updated Description");
        updatedRouteDTO.setStartTime(LocalTime.parse("09:00"));
        updatedRouteDTO.setEndTime(LocalTime.parse("15:00"));
        updatedRouteDTO.setNeighborhoodIds(Arrays.asList(1L, 2L));
        updatedRouteDTO.setStops(Arrays.asList(1L, 2L));

        when(routeService.updateRoute(any(Route.class))).thenReturn(updatedRoute);

        RouteDTO result = routeUseCase.updateRoute(updatedRouteDTO);

        assertNotNull(result);
        assertEquals(updatedRouteDTO.getId(), result.getId());
        assertEquals(updatedRouteDTO.getName(), result.getName());
        assertEquals(updatedRouteDTO.getDescription(), result.getDescription());
        assertEquals(updatedRouteDTO.getStartTime(), result.getStartTime());
        assertEquals(updatedRouteDTO.getEndTime(), result.getEndTime());
        assertEquals(updatedRouteDTO.getNeighborhoodIds(), result.getNeighborhoodIds());
        assertEquals(updatedRouteDTO.getStops(), result.getStops());

        verify(routeService, times(1)).updateRoute(any(Route.class));
    }

    @Test
    void updateRoute_RouteServiceThrowsException() {
        when(routeService.updateRoute(any(Route.class))).thenThrow(new IllegalArgumentException("Route does not exist"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeUseCase.updateRoute(routeDTO);
        });
        assertEquals("Route does not exist", exception.getMessage());

        verify(routeService, times(1)).updateRoute(any(Route.class));
    }

    @Test
    void deleteRoute_Success() {
        doNothing().when(routeService).deleteRoute(1L);
        routeUseCase.deleteRoute(1L);
        verify(routeService, times(1)).deleteRoute(1L);
    }

    @Test
    void deleteRoute_RouteServiceThrowsException() {
        doThrow(new IllegalArgumentException("Route does not exist")).when(routeService).deleteRoute(1L);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeUseCase.deleteRoute(1L);
        });
        assertEquals("Route does not exist", exception.getMessage());

        verify(routeService, times(1)).deleteRoute(1L);
    }

    @Test
    void getRouteByName_Success() {
        List<Route> routes = Arrays.asList(route);
        when(routeService.getRouteByName("Test Route")).thenReturn(routes);
        List<RouteDTO> result = routeUseCase.getRouteByName("Test Route");

        assertNotNull(result);
        assertEquals(1, result.size());
        RouteDTO resultDTO = result.get(0);
        assertEquals(routeDTO.getId(), resultDTO.getId());
        assertEquals(routeDTO.getName(), resultDTO.getName());
        assertEquals(routeDTO.getDescription(), resultDTO.getDescription());
        assertEquals(routeDTO.getStartTime(), resultDTO.getStartTime());
        assertEquals(routeDTO.getEndTime(), resultDTO.getEndTime());
        assertEquals(routeDTO.getNeighborhoodIds(), resultDTO.getNeighborhoodIds());
        assertEquals(routeDTO.getStops(), resultDTO.getStops());

        verify(routeService, times(1)).getRouteByName("Test Route");
    }

    @Test
    void getRouteByName_RouteServiceThrowsException() {
        when(routeService.getRouteByName("Test Route")).thenThrow(new IllegalArgumentException("No routes found with the name: Test Route"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeUseCase.getRouteByName("Test Route");
        });
        assertEquals("No routes found with the name: Test Route", exception.getMessage());

        verify(routeService, times(1)).getRouteByName("Test Route");
    }

    @Test
    void getRouteById_Success() {
        when(routeService.getRouteById(1L)).thenReturn(route);
        RouteDTO result = routeUseCase.getRouteById(1L);

        assertNotNull(result);
        assertEquals(routeDTO.getId(), result.getId());
        assertEquals(routeDTO.getName(), result.getName());
        assertEquals(routeDTO.getDescription(), result.getDescription());
        assertEquals(routeDTO.getStartTime(), result.getStartTime());
        assertEquals(routeDTO.getEndTime(), result.getEndTime());
        assertEquals(routeDTO.getNeighborhoodIds(), result.getNeighborhoodIds());
        assertEquals(routeDTO.getStops(), result.getStops());

        verify(routeService, times(1)).getRouteById(1L);
    }

    @Test
    void getRouteById_RouteServiceThrowsException() {
        when(routeService.getRouteById(1L)).thenThrow(new IllegalArgumentException("Route does not exist"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            routeUseCase.getRouteById(1L);
        });
        assertEquals("Route does not exist", exception.getMessage());

        verify(routeService, times(1)).getRouteById(1L);
    }
}
