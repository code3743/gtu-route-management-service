package com.gtu.route_management_service.presentation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.presentation.exception.GlobalExceptionHandler;
import com.gtu.route_management_service.application.usecase.NeighborhoodUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class NeighborhoodControllerTest {

    private MockMvc mockMvc;

    @Mock
    private NeighborhoodUseCase neighborhoodUseCase;

    @InjectMocks
    private NeighborhoodController neighborhoodController;

    private ObjectMapper objectMapper;

    private NeighborhoodDTO neighborhoodDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        neighborhoodDTO = new NeighborhoodDTO(1L, "Downtown");

        mockMvc = MockMvcBuilders.standaloneSetup(neighborhoodController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getAllNeighborhoods_Success() throws Exception {
        List<NeighborhoodDTO> neighborhoods = Arrays.asList(neighborhoodDTO);
        when(neighborhoodUseCase.getAllNeighborhoods()).thenReturn(neighborhoods);
        ResultActions result = mockMvc.perform(get("/neighborhoods")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Neighborhoods retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Downtown")));

        verify(neighborhoodUseCase, times(1)).getAllNeighborhoods();
        verify(neighborhoodUseCase, never()).searchByName(anyString());
    }

    @Test
    void getAllNeighborhoods_WithSearch_Success() throws Exception {
        List<NeighborhoodDTO> neighborhoods = Arrays.asList(neighborhoodDTO);
        when(neighborhoodUseCase.searchByName("Downtown")).thenReturn(neighborhoods);

        ResultActions result = mockMvc.perform(get("/neighborhoods")
                .param("search", "Downtown")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Neighborhoods retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Downtown")));

        verify(neighborhoodUseCase, times(1)).searchByName("Downtown");
        verify(neighborhoodUseCase, never()).getAllNeighborhoods();
    }

    @Test
    void getAllNeighborhoods_EmptyList() throws Exception {
        when(neighborhoodUseCase.getAllNeighborhoods()).thenReturn(Collections.emptyList());

        ResultActions result = mockMvc.perform(get("/neighborhoods")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Neighborhoods retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(0)));

        verify(neighborhoodUseCase, times(1)).getAllNeighborhoods();
        verify(neighborhoodUseCase, never()).searchByName(anyString());
    }

    @Test
    void getNeighborhoodById_Success() throws Exception {
        when(neighborhoodUseCase.getNeighborhoodById(1L)).thenReturn(neighborhoodDTO);

        ResultActions result = mockMvc.perform(get("/neighborhoods/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Neighborhood retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Downtown")));

        verify(neighborhoodUseCase, times(1)).getNeighborhoodById(1L);
    }

    @Test
    void getNeighborhoodById_NotFound() throws Exception {
        when(neighborhoodUseCase.getNeighborhoodById(1L)).thenReturn(null);

        ResultActions result = mockMvc.perform(get("/neighborhoods/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Neighborhood not found")))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.data").doesNotExist());

        verify(neighborhoodUseCase, times(1)).getNeighborhoodById(1L);
    }

    @Test
    void createNeighborhood_Success() throws Exception {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO();
        inputDTO.setName("New Neighborhood");
        NeighborhoodDTO createdDTO = new NeighborhoodDTO(2L, "New Neighborhood");
        when(neighborhoodUseCase.createNeighborhood(any(NeighborhoodDTO.class))).thenReturn(createdDTO);

        ResultActions result = mockMvc.perform(post("/neighborhoods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Neighborhood created successfully")))
                .andExpect(jsonPath("$.status", is(201)))
                .andExpect(jsonPath("$.data.id", is(2)))
                .andExpect(jsonPath("$.data.name", is("New Neighborhood")));

        verify(neighborhoodUseCase, times(1)).createNeighborhood(any(NeighborhoodDTO.class));
    }

    @Test
    void deleteNeighborhood_Success() throws Exception {
        doNothing().when(neighborhoodUseCase).deleteNeighborhood(1L);

        ResultActions result = mockMvc.perform(delete("/neighborhoods/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Neighborhood deleted successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", is(1)));

        verify(neighborhoodUseCase, times(1)).deleteNeighborhood(1L);
    }

    @Test
    void updateNeighborhood_Success() throws Exception {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO(1L, "Uptown");
        NeighborhoodDTO updatedDTO = new NeighborhoodDTO(1L, "Uptown");
        when(neighborhoodUseCase.updateNeighborhood(any(NeighborhoodDTO.class))).thenReturn(updatedDTO);

        ResultActions result = mockMvc.perform(put("/neighborhoods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Neighborhood updated successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Uptown")));

        verify(neighborhoodUseCase, times(1)).updateNeighborhood(any(NeighborhoodDTO.class));
    }

    @Test
    void updateNeighborhood_NotFound_ThrowsException() throws Exception {
        NeighborhoodDTO inputDTO = new NeighborhoodDTO(1L, "Uptown");
        when(neighborhoodUseCase.updateNeighborhood(any(NeighborhoodDTO.class)))
                .thenThrow(new IllegalArgumentException("Neighborhood does not exist"));

        ResultActions result = mockMvc.perform(put("/neighborhoods")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Invalid Argument")))
                .andExpect(jsonPath("$.message", is("Neighborhood does not exist")))
                .andExpect(jsonPath("$.path", is("/neighborhoods")));

        verify(neighborhoodUseCase, times(1)).updateNeighborhood(any(NeighborhoodDTO.class));
    }
}
