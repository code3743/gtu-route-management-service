package com.gtu.route_management_service.presentation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import com.gtu.route_management_service.application.dto.RouteDTO;
import com.gtu.route_management_service.application.usecase.RouteUseCase;
import com.gtu.route_management_service.presentation.exception.GlobalExceptionHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RouteUseCase routeUseCase;

    @InjectMocks
    private RouteController routeController;

    private ObjectMapper objectMapper;

    private RouteDTO routeDTO;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        SimpleModule timeModule = new SimpleModule();
        timeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm")));
        timeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm")));
        objectMapper.registerModule(timeModule);

        mockMvc = MockMvcBuilders.standaloneSetup(routeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();

        routeDTO = new RouteDTO();
        routeDTO.setId(1L);
        routeDTO.setName("Test Route");
        routeDTO.setDescription("Test Description");
        routeDTO.setStartTime(LocalTime.parse("08:00"));
        routeDTO.setEndTime(LocalTime.parse("16:00"));
        routeDTO.setNeighborhoodIds(Arrays.asList(1L, 2L));
        routeDTO.setStops(Arrays.asList(1L, 2L));
    }

    @Test
    void createRoute_Success() throws Exception {
        when(routeUseCase.createRoute(any(RouteDTO.class))).thenReturn(routeDTO);

        mockMvc.perform(post("/routes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Route created successfully")))
                .andExpect(jsonPath("$.status", is(201)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Test Route")))
                .andExpect(jsonPath("$.data.description", is("Test Description")))
                .andExpect(jsonPath("$.data.startTime", is("08:00")))
                .andExpect(jsonPath("$.data.endTime", is("16:00")))
                .andExpect(jsonPath("$.data.neighborhoodIds", contains(1, 2)))
                .andExpect(jsonPath("$.data.stops", contains(1, 2)));
            
        verify(routeUseCase, times(1)).createRoute(any(RouteDTO.class));
    }

    @Test
    void getAllRoutes_Success() throws Exception {
        List<RouteDTO> routes = Arrays.asList(routeDTO);
        when(routeUseCase.getAllRoutes()).thenReturn(routes);

        mockMvc.perform(get("/routes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Routes retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Test Route")))
                .andExpect(jsonPath("$.data[0].description", is("Test Description")))
                .andExpect(jsonPath("$.data[0].startTime", is("08:00")))
                .andExpect(jsonPath("$.data[0].endTime", is("16:00")))
                .andExpect(jsonPath("$.data[0].neighborhoodIds", contains(1, 2)))
                .andExpect(jsonPath("$.data[0].stops", contains(1, 2)));

        verify(routeUseCase, times(1)).getAllRoutes();
    }

    @Test
    void getAllRoutes_EmptyList() throws Exception {
        when(routeUseCase.getAllRoutes()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/routes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Routes retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(0)));

        verify(routeUseCase, times(1)).getAllRoutes();
    }

    @Test
    void updateRoute_Success() throws Exception {
        RouteDTO updatedRouteDTO = new RouteDTO();
        updatedRouteDTO.setId(1L);
        updatedRouteDTO.setName("Updated Route");
        updatedRouteDTO.setDescription("Updated Description");
        updatedRouteDTO.setStartTime(LocalTime.parse("09:00"));
        updatedRouteDTO.setEndTime(LocalTime.parse("19:00"));
        updatedRouteDTO.setNeighborhoodIds(Arrays.asList(1L, 2L));
        updatedRouteDTO.setStops(Arrays.asList(1L, 2L));

        when(routeUseCase.updateRoute(any(RouteDTO.class))).thenReturn(updatedRouteDTO);

        mockMvc.perform(put("/routes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(routeDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Route updated successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Updated Route")))
                .andExpect(jsonPath("$.data.description", is("Updated Description")))
                .andExpect(jsonPath("$.data.startTime", is("09:00")))
                .andExpect(jsonPath("$.data.endTime", is("19:00")))
                .andExpect(jsonPath("$.data.neighborhoodIds", contains(1, 2)))
                .andExpect(jsonPath("$.data.stops", contains(1, 2)));
            
        verify(routeUseCase, times(1)).updateRoute(argThat(dto -> dto.getId().equals(1L)));
    }

    @Test
    void deleteRoute_Success() throws Exception {
        doNothing().when(routeUseCase).deleteRoute(1L);

        mockMvc.perform(delete("/routes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Route deleted successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data").doesNotExist());

        verify(routeUseCase, times(1)).deleteRoute(1L);
    }

    @Test
    void findRoutesByName_Success() throws Exception {
        List<RouteDTO> routes = Arrays.asList(routeDTO);
        when(routeUseCase.getRouteByName("Test Route")).thenReturn(routes);

        mockMvc.perform(get("/routes/search")
                .param("name", "Test Route"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Routes retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].name", is("Test Route")))
                .andExpect(jsonPath("$.data[0].description", is("Test Description")))
                .andExpect(jsonPath("$.data[0].startTime", is("08:00")))
                .andExpect(jsonPath("$.data[0].endTime", is("16:00")))
                .andExpect(jsonPath("$.data[0].neighborhoodIds", contains(1, 2)))
                .andExpect(jsonPath("$.data[0].stops", contains(1, 2)));
                
        verify(routeUseCase, times(1)).getRouteByName("Test Route");
    }

    @Test
    void getRouteById_Success() throws Exception {
        when(routeUseCase.getRouteById(1L)).thenReturn(routeDTO);

        mockMvc.perform(get("/routes/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Route retrieved successfully")))
                .andExpect(jsonPath("$.status", is(200)))
                .andExpect(jsonPath("$.data.id", is(1)))
                .andExpect(jsonPath("$.data.name", is("Test Route")))
                .andExpect(jsonPath("$.data.description", is("Test Description")))
                .andExpect(jsonPath("$.data.startTime", is("08:00")))
                .andExpect(jsonPath("$.data.endTime", is("16:00")))
                .andExpect(jsonPath("$.data.neighborhoodIds", contains(1, 2)))
                .andExpect(jsonPath("$.data.stops", contains(1, 2)));
                
        verify(routeUseCase, times(1)).getRouteById(1L);
    }
}
