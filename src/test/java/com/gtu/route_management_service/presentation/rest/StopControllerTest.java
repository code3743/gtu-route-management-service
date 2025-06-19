package com.gtu.route_management_service.presentation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.presentation.exception.GlobalExceptionHandler;
import com.gtu.route_management_service.application.usecase.StopUseCase;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class StopControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StopUseCase stopUseCase;

    @InjectMocks
    private StopController stopController;

    private ObjectMapper objectMapper;

    private StopDTO stopDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        stopDTO = new StopDTO(1L, "Main Stop", "Central stop", 1L, 40.7128, -74.0060);

        mockMvc = MockMvcBuilders.standaloneSetup(stopController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void createStop_Success() throws Exception {
        StopDTO inputDTO = new StopDTO(null, "New Stop", "New description", 1L, 40.7128, -74.0060);
        StopDTO createdDTO = new StopDTO(2L, "New Stop", "New description", 1L, 40.7128, -74.0060);
        when(stopUseCase.createStop(any(StopDTO.class))).thenReturn(createdDTO);

        ResultActions result = mockMvc.perform(post("/stops")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Stop created successfully")))
                .andExpect(jsonPath("$.status", is(201)))
                .andExpect(jsonPath("$.data.id", is(2)))
                .andExpect(jsonPath("$.data.name", is("New Stop")));

        verify(stopUseCase, times(1)).createStop(any(StopDTO.class));
    }

    @Test
    void createStop_NeighborhoodNotFound_ThrowsException() throws Exception {
        StopDTO inputDTO = new StopDTO(null, "New Stop", "New description", 1L, 40.7128, -74.0060);
        when(stopUseCase.createStop(any(StopDTO.class)))
                .thenThrow(new IllegalArgumentException("Neighborhood does not exist"));

        ResultActions result = mockMvc.perform(post("/stops")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Invalid Argument")))
                .andExpect(jsonPath("$.message", is("Neighborhood does not exist")))
                .andExpect(jsonPath("$.path", is("/stops")));

        verify(stopUseCase, times(1)).createStop(any(StopDTO.class));
    }

    @Test
    void getAllStops_Success() throws Exception {
        List<StopDTO> stops = Arrays.asList(stopDTO);
        when(stopUseCase.getAllStops()).thenReturn(stops);

        ResultActions result = mockMvc.perform(get("/stops")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Stops retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Main Stop")));

        verify(stopUseCase, times(1)).getAllStops();
        verify(stopUseCase, never()).searchByName(anyString());
    }

    @Test
    void getAllStops_WithSearch_Success() throws Exception {
        List<StopDTO> stops = Arrays.asList(stopDTO);
        when(stopUseCase.searchByName("Main")).thenReturn(stops);

        ResultActions result = mockMvc.perform(get("/stops")
                .param("search", "Main")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Stops retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Main Stop")));

        verify(stopUseCase, times(1)).searchByName("Main");
        verify(stopUseCase, never()).getAllStops();
    }

    @Test
    void getAllStops_EmptyList() throws Exception {
        when(stopUseCase.getAllStops()).thenReturn(Collections.emptyList());

        ResultActions result = mockMvc.perform(get("/stops")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Stops retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(0)));

        verify(stopUseCase, times(1)).getAllStops();
        verify(stopUseCase, never()).searchByName(anyString());
    }

    @Test
    void getStopById_Success() throws Exception {
        when(stopUseCase.getStopById(1L)).thenReturn(stopDTO);

        ResultActions result = mockMvc.perform(get("/stops/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Stop retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Main Stop")));

        verify(stopUseCase, times(1)).getStopById(1L);
    }

    @Test
    void getStopById_NotFound() throws Exception {
        when(stopUseCase.getStopById(1L)).thenReturn(null);

        ResultActions result = mockMvc.perform(get("/stops/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Stop not found")))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.data").doesNotExist());

        verify(stopUseCase, times(1)).getStopById(1L);
    }

    @Test
    void deleteStop_Success() throws Exception {
        doNothing().when(stopUseCase).deleteStop(1L);

        ResultActions result = mockMvc.perform(delete("/stops/1")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Stop deleted successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").doesNotExist());

        verify(stopUseCase, times(1)).deleteStop(1L);
    }

    @Test
    void updateStop_Success() throws Exception {
        StopDTO inputDTO = new StopDTO(1L, "Updated Stop", "Updated description", 1L, 41.7128, -75.0060);
        StopDTO updatedDTO = new StopDTO(1L, "Updated Stop", "Updated description", 1L, 41.7128, -75.0060);
        when(stopUseCase.updateStop(any(StopDTO.class))).thenReturn(updatedDTO);

        ResultActions result = mockMvc.perform(put("/stops")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Stop updated successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Updated Stop")));

        verify(stopUseCase, times(1)).updateStop(any(StopDTO.class));
    }

    @Test
    void updateStop_StopNotFound_ThrowsException() throws Exception {
        StopDTO inputDTO = new StopDTO(1L, "Updated Stop", "Updated description", 1L, 41.7128, -75.0060);
        when(stopUseCase.updateStop(any(StopDTO.class)))
                .thenThrow(new IllegalArgumentException("Stop does not exist"));

        ResultActions result = mockMvc.perform(put("/stops")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDTO)));

        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.error", is("Invalid Argument")))
                .andExpect(jsonPath("$.message", is("Stop does not exist")))
                .andExpect(jsonPath("$.path", is("/stops")));

        verify(stopUseCase, times(1)).updateStop(any(StopDTO.class));
    }
}
