package com.gtu.route_management_service.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.service.NeighborhoodService;

@ExtendWith(MockitoExtension.class)
class NeighborhoodUseCaseTest {
    @Mock
    private NeighborhoodService neighborhoodService;

    @InjectMocks
    private NeighborhoodUseCase neighborhoodUseCase;

    private Neighborhood neighborhood;
    private NeighborhoodDTO neighborhoodDTO;

    @BeforeEach
    void setUp() {
        neighborhood = new Neighborhood(1L, "Downtown");
        neighborhoodDTO = new NeighborhoodDTO();
        neighborhoodDTO.setId(1L);
        neighborhoodDTO.setName("Downtown");
    }

    @Test
    void createNeighborhood_Success() {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO();
        inputDTO.setName("New Neighborhood");
        Neighborhood savedNeighborhood = new Neighborhood(2L, "New Neighborhood");
        when(neighborhoodService.createNeighborhood(any(Neighborhood.class))).thenReturn(savedNeighborhood);
        
        NeighborhoodDTO result = neighborhoodUseCase.createNeighborhood(inputDTO);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("New Neighborhood", result.getName());
        verify(neighborhoodService, times(1)).createNeighborhood(any(Neighborhood.class));
    }

    @Test
    void createNeighborhood_NullName_ThrowsException() {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO();
        inputDTO.setName(null);

        when(neighborhoodService.createNeighborhood(any(Neighborhood.class)))
                .thenThrow(new IllegalArgumentException("Neighborhood name cannot be null or empty"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodUseCase.createNeighborhood(inputDTO);
        });
        assertEquals("Neighborhood name cannot be null or empty", exception.getMessage());
        verify(neighborhoodService, times(1)).createNeighborhood(any(Neighborhood.class));
    }

    @Test
    void createNeighborhood_EmptyName_ThrowsException() {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO();
        inputDTO.setName("  ");

        when(neighborhoodService.createNeighborhood(any(Neighborhood.class)))
                .thenThrow(new IllegalArgumentException("Neighborhood name cannot be null or empty"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodUseCase.createNeighborhood(inputDTO);
        });
        assertEquals("Neighborhood name cannot be null or empty", exception.getMessage());
        verify(neighborhoodService, times(1)).createNeighborhood(any(Neighborhood.class));
    }

    @Test
    void getNeighborhoodById_Success() {
        when(neighborhoodService.getNeighborhoodById(1L)).thenReturn(Optional.of(neighborhood));
        NeighborhoodDTO result = neighborhoodUseCase.getNeighborhoodById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Downtown", result.getName());
        verify(neighborhoodService, times(1)).getNeighborhoodById(1L);
    }

    @Test
    void getNeighborhoodById_NotFound() {
        when(neighborhoodService.getNeighborhoodById(1L)).thenReturn(Optional.empty());
        NeighborhoodDTO result = neighborhoodUseCase.getNeighborhoodById(1L);

        assertNull(result);
        verify(neighborhoodService, times(1)).getNeighborhoodById(1L);
    }

    @Test
    void deleteNeighborhood_Success() {
        doNothing().when(neighborhoodService).deleteNeighborhood(1L);
        neighborhoodUseCase.deleteNeighborhood(1L);

        verify(neighborhoodService, times(1)).deleteNeighborhood(1L);
    }

    @Test
    void updateNeighborhood_Success() {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO(1L, "Updated Neighborhood");
        Neighborhood updatedNeighborhood = new Neighborhood(1L, "Updated Neighborhood");
        when(neighborhoodService.updateNeighborhood(any(Neighborhood.class))).thenReturn(updatedNeighborhood);

        NeighborhoodDTO result = neighborhoodUseCase.updateNeighborhood(inputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Neighborhood", result.getName());
        verify(neighborhoodService, times(1)).updateNeighborhood(any(Neighborhood.class));
    }

    @Test
    void updatedNeighborhood_NotFound_ThrowsException() {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO(1L, "Updated Neighborhood");

        when(neighborhoodService.updateNeighborhood(any(Neighborhood.class)))
            .thenThrow(new IllegalArgumentException("Neighborhood does not exist"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodUseCase.updateNeighborhood(inputDTO);
        });

        assertEquals("Neighborhood does not exist", exception.getMessage());
        verify(neighborhoodService, times(1)).updateNeighborhood(any(Neighborhood.class));
    }

    @Test
    void updateNeighborhood_EmptyName_ThrowsException() {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO(1L, "  ");
        when(neighborhoodService.updateNeighborhood(any(Neighborhood.class)))
                .thenThrow(new IllegalArgumentException("Neighborhood name cannot be null or empty"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            neighborhoodUseCase.updateNeighborhood(inputDTO);
        });
        assertEquals("Neighborhood name cannot be null or empty", exception.getMessage());
        verify(neighborhoodService, times(1)).updateNeighborhood(any(Neighborhood.class));
    }

    @Test
    void getAllNeighborhoods_Success() {
        List<Neighborhood> neighborhoods = Arrays.asList(neighborhood);
        when(neighborhoodService.getAllNeighborhoods()).thenReturn(neighborhoods);
        List<NeighborhoodDTO> result = neighborhoodUseCase.getAllNeighborhoods();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Downtown", result.get(0).getName());
        verify(neighborhoodService, times(1)).getAllNeighborhoods();
    }

    @Test
    void getAllNeighborhoods_EmptyList() {
        when(neighborhoodService.getAllNeighborhoods()).thenReturn(Collections.emptyList());
        List<NeighborhoodDTO> result = neighborhoodUseCase.getAllNeighborhoods();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodService, times(1)).getAllNeighborhoods();
    }

    @Test
    void getAllNeighborhoods_NullList() {
        when(neighborhoodService.getAllNeighborhoods()).thenReturn(null);
        List<NeighborhoodDTO> result = neighborhoodUseCase.getAllNeighborhoods();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodService, times(1)).getAllNeighborhoods();
    }

    @Test
    void getNeighborhoodsByIds_Success() {
        List<Long> ids = Arrays.asList(1L);
        List<Neighborhood> neighborhoods = Arrays.asList(neighborhood);
        when(neighborhoodService.getNeighborhoodsByIds(ids)).thenReturn(neighborhoods);
        List<NeighborhoodDTO> result = neighborhoodUseCase.getNeighborhoodsByIds(ids);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Downtown", result.get(0).getName());
        verify(neighborhoodService, times(1)).getNeighborhoodsByIds(ids);
    }

    @Test
    void getNeighborhoodsByIds_EmptyList() {
        List<Long> ids = Arrays.asList(1L);
        when(neighborhoodService.getNeighborhoodsByIds(ids)).thenReturn(Collections.emptyList());
        List<NeighborhoodDTO> result = neighborhoodUseCase.getNeighborhoodsByIds(ids);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodService, times(1)).getNeighborhoodsByIds(ids);
    }

    @Test
    void getNeighborhoodsByIds_NullList() {
        List<Long> ids = Arrays.asList(1L);
        when(neighborhoodService.getNeighborhoodsByIds(ids)).thenReturn(null);
        List<NeighborhoodDTO> result = neighborhoodUseCase.getNeighborhoodsByIds(ids);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodService, times(1)).getNeighborhoodsByIds(ids);
    }

    @Test
    void searchByName_Success() {
        List<Neighborhood> neighborhoods = Arrays.asList(neighborhood);
        when(neighborhoodService.searchByName("Downtown")).thenReturn(neighborhoods);
        List<NeighborhoodDTO> result = neighborhoodUseCase.searchByName("Downtown");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Downtown", result.get(0).getName());
        verify(neighborhoodService, times(1)).searchByName("Downtown");
    }

    @Test
    void searchByName_NoResults() {
        when(neighborhoodService.searchByName("Unknown")).thenReturn(Collections.emptyList());
        List<NeighborhoodDTO> result = neighborhoodUseCase.searchByName("Unknown");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodService, times(1)).searchByName("Unknown");
    }

    @Test
    void searchByName_NullList() {
        when(neighborhoodService.searchByName("Unknown")).thenReturn(null);
        List<NeighborhoodDTO> result = neighborhoodUseCase.searchByName("Unknown");

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(neighborhoodService, times(1)).searchByName("Unknown");
    }
}



