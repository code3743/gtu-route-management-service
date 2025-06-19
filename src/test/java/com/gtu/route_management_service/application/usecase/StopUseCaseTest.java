package com.gtu.route_management_service.application.usecase;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.service.StopService;

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
class StopUseCaseTest {

    @Mock
    private StopService stopService;

    @InjectMocks
    private StopUseCase stopUseCase;

    private Stop stop;
    private StopDTO stopDTO;

    @BeforeEach
    void setUp() {
        stop = new Stop(1L, "Main Stop", "Central stop", 1L, 40.7128, -74.0060);
        stopDTO = new StopDTO(1L, "Main Stop", "Central stop", 1L, 40.7128, -74.0060);
    }

    @Test
    void createStop_Success() {
        when(stopService.createStop(any(Stop.class))).thenReturn(stop);
        StopDTO result = stopUseCase.createStop(stopDTO);

        assertNotNull(result);
        assertEquals("Main Stop", result.getName());
        assertEquals("Central stop", result.getDescription());
        assertEquals(1L, result.getNeighborhoodId());
        assertEquals(40.7128, result.getLatitude());
        assertEquals(-74.0060, result.getLongitude());
        verify(stopService, times(1)).createStop(any(Stop.class));
    }

    @Test
    void createStop_NullDTO_ReturnsNull() {
        StopDTO result = stopUseCase.createStop(null);

        assertNull(result);
        verify(stopService, never()).createStop(any(Stop.class));
    }

    @Test
    void getAllStops_Success() {
        List<Stop> stops = Arrays.asList(stop);
        when(stopService.getAllStops()).thenReturn(stops);
        List<StopDTO> result = stopUseCase.getAllStops();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Main Stop", result.get(0).getName());
        verify(stopService, times(1)).getAllStops();
    }

    @Test
    void getAllStops_EmptyList() {
        when(stopService.getAllStops()).thenReturn(Collections.emptyList());
        List<StopDTO> result = stopUseCase.getAllStops();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(stopService, times(1)).getAllStops();
    }

    @Test
    void getAllStops_NullList_ReturnsEmptyList() {
        when(stopService.getAllStops()).thenReturn(null);
        List<StopDTO> result = stopUseCase.getAllStops();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(stopService, times(1)).getAllStops();
    }

    @Test
    void getStopById_Success() {
        when(stopService.getStopById(1L)).thenReturn(Optional.of(stop));
        StopDTO result = stopUseCase.getStopById(1L);

        assertNotNull(result);
        assertEquals("Main Stop", result.getName());
        verify(stopService, times(1)).getStopById(1L);
    }

    @Test
    void getStopById_NotFound() {
        when(stopService.getStopById(1L)).thenReturn(Optional.empty());
        StopDTO result = stopUseCase.getStopById(1L);

        assertNull(result);
        verify(stopService, times(1)).getStopById(1L);
    }

    @Test
    void deleteStop_Success() {
        doNothing().when(stopService).deleteStop(1L);
        stopUseCase.deleteStop(1L);

        verify(stopService, times(1)).deleteStop(1L);
    }

    @Test
    void updateStop_Success() {
        Stop updatedStop = new Stop(1L, "Updated Stop", "Updated description", 1L, 41.7128, -75.0060);
        when(stopService.updateStop(any(Stop.class))).thenReturn(updatedStop);
        StopDTO result = stopUseCase.updateStop(stopDTO);

        assertNotNull(result);
        assertEquals("Updated Stop", result.getName());
        assertEquals("Updated description", result.getDescription());
        assertEquals(1L, result.getNeighborhoodId());
        assertEquals(41.7128, result.getLatitude());
        assertEquals(-75.0060, result.getLongitude());
        verify(stopService, times(1)).updateStop(any(Stop.class));
    }

    @Test
    void updateStop_NullDTO_ReturnsNull() {
        StopDTO result = stopUseCase.updateStop(null);

        assertNull(result);
        verify(stopService, never()).updateStop(any(Stop.class));
    }

    @Test
    void searchByName_Success() {
        List<Stop> stops = Arrays.asList(stop);
        when(stopService.searchByName("Main")).thenReturn(stops);
        List<StopDTO> result = stopUseCase.searchByName("Main");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Main Stop", result.get(0).getName());
        verify(stopService, times(1)).searchByName("Main");
    }

    @Test
    void searchByName_EmptyList() {
        when(stopService.searchByName("Main")).thenReturn(Collections.emptyList());
        List<StopDTO> result = stopUseCase.searchByName("Main");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(stopService, times(1)).searchByName("Main");
    }

    @Test
    void searchByName_NullList_ReturnsEmptyList() {
        when(stopService.searchByName("Main")).thenReturn(null);
        List<StopDTO> result = stopUseCase.searchByName("Main");
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(stopService, times(1)).searchByName("Main");
    }
}
