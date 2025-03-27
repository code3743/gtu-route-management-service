package com.gtu.route_management_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    @NotEmpty(message = "The route name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "The start time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "The end time is mandatory")
    private LocalTime endTime;

    @NotEmpty(message = "The list of neighbothoods cannot be empty")
    private List<Long> neighborhoods;

    @NotEmpty(message = "The stop list cannot be empty")
    @Size(min = 2, message = "The route must have at least two stops")
    private List<Long> stops;
}
