package com.gtu.route_management_service.application.service;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import com.gtu.route_management_service.domain.repository.StopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopServiceImplTest {

    @Mock
    private StopRepository stopRepository;

    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    private StopServiceImpl stopService;

    private Stop stop;

    @BeforeEach
    void setUp() {
        stop = new Stop(1L, "Main Stop", "Central stop", 1L, 40.7128, -74.0060);
    }

    @Test
    void createStop_Success() {
        Stop inputStop = new Stop("Main Stop", "Central stop", 1L, 40.7128, -74.0060);
        when(neighborhoodRepository.existsById(1L)).thenReturn(true);
        when(stopRepository.save(any(Stop.class))).thenReturn(stop);
        Stop createdStop = stopService.createStop(inputStop);

        assertNotNull(createdStop);
        assertEquals("Main Stop", createdStop.getName());
        assertEquals("Central stop", createdStop.getDescription());
        assertEquals(1L, createdStop.getNeighborhoodId());
        assertEquals(40.7128, createdStop.getLatitude());
        assertEquals(-74.0060, createdStop.getLongitude());
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(stopRepository, times(1)).save(inputStop);
    }

    @Test
    void createStop_EmptyName_ThrowsException() {
        Stop inputStop = new Stop("", "Central stop", 1L, 40.7128, -74.0060);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stopService.createStop(inputStop);
        });

        assertEquals("Stop name cannot be empty", exception.getMessage());
        verify(neighborhoodRepository, never()).existsById(anyLong());
        verify(stopRepository, never()).save(any(Stop.class));
    }

    @Test
    void createStop_NeighborhoodNotFound_ThrowsException() {
        Stop inputStop = new Stop("Main Stop", "Central stop", 1L, 40.7128, -74.0060);
        when(neighborhoodRepository.existsById(1L)).thenReturn(false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stopService.createStop(inputStop);
        });

        assertEquals("Neighborhood does not exist", exception.getMessage());
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(stopRepository, never()).save(any(Stop.class));
    }

    @Test
    void getAllStops_Success() {
        List<Stop> stops = Arrays.asList(stop);
        when(stopRepository.findAll()).thenReturn(stops);
        List<Stop> result = stopService.getAllStops();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Main Stop", result.get(0).getName());
        verify(stopRepository, times(1)).findAll();
    }

    @Test
    void getAllStops_EmptyList() {
        when(stopRepository.findAll()).thenReturn(Collections.emptyList());
        List<Stop> result = stopService.getAllStops();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(stopRepository, times(1)).findAll();
    }

    @Test
    void getStopById_Success() {
        when(stopRepository.findById(1L)).thenReturn(Optional.of(stop));
        Optional<Stop> result = stopService.getStopById(1L);

        assertTrue(result.isPresent());
        assertEquals("Main Stop", result.get().getName());
        verify(stopRepository, times(1)).findById(1L);
    }

    @Test
    void getStopById_NotFound() {
        when(stopRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Stop> result = stopService.getStopById(1L);

        assertFalse(result.isPresent());
        verify(stopRepository, times(1)).findById(1L);
    }

    @Test
    void deleteStop_Success() {
        doNothing().when(stopRepository).deleteById(1L);
        stopService.deleteStop(1L);

        verify(stopRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateStop_Success() {
        Stop updatedStop = new Stop(1L, "Updated Stop", "Updated description", 1L, 41.7128, -75.0060);
        when(stopRepository.existsById(1L)).thenReturn(true);
        when(neighborhoodRepository.existsById(1L)).thenReturn(true);
        when(stopRepository.update(any(Stop.class))).thenReturn(updatedStop);
        Stop result = stopService.updateStop(stop);

        assertNotNull(result);
        assertEquals("Updated Stop", result.getName());
        assertEquals("Updated description", result.getDescription());
        assertEquals(1L, result.getNeighborhoodId());
        assertEquals(41.7128, result.getLatitude());
        assertEquals(-75.0060, result.getLongitude());
        verify(stopRepository, times(1)).existsById(1L);
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(stopRepository, times(1)).update(stop);
    }

    @Test
    void updateStop_StopNotFound_ThrowsException() {
        when(stopRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stopService.updateStop(stop);
        });

        assertEquals("Stop does not exist", exception.getMessage());
        verify(stopRepository, times(1)).existsById(1L);
        verify(neighborhoodRepository, never()).existsById(anyLong());
        verify(stopRepository, never()).update(any(Stop.class));
    }

    @Test
    void updateStop_NeighborhoodNotFound_ThrowsException() {
        when(stopRepository.existsById(1L)).thenReturn(true);
        when(neighborhoodRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            stopService.updateStop(stop);
        });

        assertEquals("Neighborhood does not exist", exception.getMessage());
        verify(stopRepository, times(1)).existsById(1L);
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(stopRepository, never()).update(any(Stop.class));
    }

    @Test
    void searchByName_Success() {
        List<Stop> stops = Arrays.asList(stop);
        when(stopRepository.searchByName("Main")).thenReturn(stops);
        List<Stop> result = stopService.searchByName("Main");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Main Stop", result.get(0).getName());
        verify(stopRepository, times(1)).searchByName("Main");
    }

    @Test
    void searchByName_EmptyList() {
        when(stopRepository.searchByName("Main")).thenReturn(Collections.emptyList());
        List<Stop> result = stopService.searchByName("Main");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(stopRepository, times(1)).searchByName("Main");
    }
}