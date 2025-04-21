package com.gtu.route_management_service.application.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class NeighborhoodServiceImplTest {
    @Mock
    private NeighborhoodRepository neighborhoodRepository;

    @InjectMocks
    private NeighborhoodServiceImpl neighborhoodService;

    private Neighborhood neighborhood;

    @BeforeEach
    void setUp() {
        neighborhood = new Neighborhood(1L, "Downtown");
    }

    @Test
    void createNeighborhood_Success() {
        Neighborhood newNeighborhood = new Neighborhood("New Neighborhood");
        Neighborhood savedNeighborhood = new Neighborhood(2L, "New Neighborhood");
        when(neighborhoodRepository.save(any(Neighborhood.class))).thenReturn(savedNeighborhood);

        Neighborhood result = neighborhoodService.createNeighborhood(newNeighborhood);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("New Neighborhood", result.getName());
        verify(neighborhoodRepository, times(1)).save(newNeighborhood);
    }

    @Test
    void createNeighborhood_EmptyName_ThrowException() {
        Neighborhood invalidNeighborhood = new Neighborhood("");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodService.createNeighborhood(invalidNeighborhood);
        });
        assertEquals("Neighborhood name cannot be empty", exception.getMessage());
        verify(neighborhoodRepository, never()).save(any(Neighborhood.class));
    }

    @Test
    void getAllNeighborhoods_Success() {
        List<Neighborhood> neighborhoods = Arrays.asList(neighborhood);
        when(neighborhoodRepository.findAll()).thenReturn(neighborhoods);
        List<Neighborhood> result = neighborhoodService.getAllNeighborhoods();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Downtown", result.get(0).getName());
        verify(neighborhoodRepository, times(1)).findAll();
    }

    @Test
    void getAllNeighborhoods_EmptyList() {
        when(neighborhoodRepository.findAll()).thenReturn(Collections.emptyList());
        List<Neighborhood> result = neighborhoodService.getAllNeighborhoods();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodRepository, times(1)).findAll();
    }

    @Test
    void getNeighborhoodsByIds_Success() {
        List<Long> ids = Arrays.asList(1L);
        List<Neighborhood> neighborhoods = Arrays.asList(neighborhood);
        when(neighborhoodRepository.findAllById(ids)).thenReturn(neighborhoods);

        List<Neighborhood> result = neighborhoodService.getNeighborhoodsByIds(ids);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Downtown", result.get(0).getName());
        verify(neighborhoodRepository, times(1)).findAllById(ids);
    }

    @Test
    void getNeighborhoodsIds_EmptyList() {
        List<Long> ids = Arrays.asList(1L);
        when(neighborhoodRepository.findAllById(ids)).thenReturn(Collections.emptyList());
        List<Neighborhood> result = neighborhoodService.getNeighborhoodsByIds(ids);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodRepository, times(1)).findAllById(ids);
    }

    @Test
    void deleteNeighborhood_Success() {
        doNothing().when(neighborhoodRepository).deleteById(1L);
        neighborhoodService.deleteNeighborhood(1L);

        verify(neighborhoodRepository, times(1)).deleteById(1L);
    }

    @Test
    void getNeighborhoodById_Success() {
        when(neighborhoodRepository.findById(1L)).thenReturn(Optional.of(neighborhood));
        Optional<Neighborhood> result = neighborhoodService.getNeighborhoodById(1L);

        assertTrue(result.isPresent());
        assertEquals("Downtown", result.get().getName());
        verify(neighborhoodRepository, times(1)).findById(1L);
    }

    @Test
    void getNeighborhoodById_NotFound() {
        when(neighborhoodRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Neighborhood> result = neighborhoodService.getNeighborhoodById(1L);

        assertFalse(result.isPresent());
        verify(neighborhoodRepository, times(1)).findById(1L);
    }

    @Test
    void updateNeighborhood_Success() {
        Neighborhood updatedNeighborhood = new Neighborhood(1L, "Updated Neighborhood");
        when(neighborhoodRepository.existsById(1L)).thenReturn(true);
        when(neighborhoodRepository.update(any(Neighborhood.class))).thenReturn(updatedNeighborhood);
        Neighborhood result = neighborhoodService.updateNeighborhood(updatedNeighborhood);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Neighborhood", result.getName());
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(neighborhoodRepository, times(1)).update(updatedNeighborhood);
    }

    @Test
    void updatedNeighborhood_NotFound_ThrowException() {
        when(neighborhoodRepository.existsById(1L)).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodService.updateNeighborhood(neighborhood);
        });

        assertEquals("Neighborhood does not exist", exception.getMessage());
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(neighborhoodRepository, never()).update(any(Neighborhood.class));
    }

    @Test
    void updateNeighborhood_EmptyName_ThrowsException() {
        Neighborhood invalidNeighborhood = new Neighborhood(1L, "");
        when(neighborhoodRepository.existsById(1L)).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodService.updateNeighborhood(invalidNeighborhood);
        });

        assertEquals("Neighborhood name cannot be empty", exception.getMessage());
        verify(neighborhoodRepository, times(1)).existsById(1L);
        verify(neighborhoodRepository, never()).update(any(Neighborhood.class));
    }

    @Test
    void searchByName_Success() {
        List<Neighborhood> neighborhoods = Arrays.asList(neighborhood);
        when(neighborhoodRepository.searchByName("Downtown")).thenReturn(neighborhoods);
        List<Neighborhood> result = neighborhoodService.searchByName("Downtown");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Downtown", result.get(0).getName());
        verify(neighborhoodRepository, times(1)).searchByName("Downtown");
    }

    @Test
    void searchByName_NoResults() {
        when(neighborhoodRepository.searchByName("Nonexistent")).thenReturn(Collections.emptyList());
        List<Neighborhood> result = neighborhoodService.searchByName("Nonexistent");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodRepository, times(1)).searchByName("Nonexistent");
    }
}