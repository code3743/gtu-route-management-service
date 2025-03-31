package com.gtu.route_management_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO that represents a route in the transportation system")
public class RouteDTO {
    @Schema(description = "Unique identifier of the route", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Name of the route", example = "Route 1")
    @NotEmpty(message = "The route name cannot be empty")
    private String name;

    @Schema(description = "Description of the route", example = "This route connects the main neighborhoods")
    private String description;

    @Schema(description = "Start time of the route", example = "08:00")
    @NotNull(message = "The start time is mandatory")
    private LocalTime startTime;

    @Schema(description = "End time of the route", example = "20:00")
    @NotNull(message = "The end time is mandatory")
    private LocalTime endTime;

    @Schema(description = "List of neighborhood IDs that the route passes through", example = "[1, 2, 3]")
    @NotEmpty(message = "The list of neighbothoods cannot be empty")
    private List<Long> neighborhoodIds;

    @Schema(description = "List of stop IDs that the route passes through", example = "[1, 2, 3]")
    @NotEmpty(message = "The stop list cannot be empty")
    @Size(min = 2, message = "The route must have at least two stops")
    private List<Long> stops;
}
