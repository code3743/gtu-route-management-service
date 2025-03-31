package com.gtu.route_management_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO that represents a stop in the transportation system")
public class StopDTO {

    @Schema(description = "Unique identifier of the stop", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @Schema(description = "Name of the stop", example = "Main Street")
    private String name;

    @Schema(description = "Description of the stop", example = "This stop is located at Main Street and 1st Avenue")
    private String description;

    @Schema(description = "Neighborhood ID that the stop belongs to", example = "1")
    private Long neighborhoodId;

    @Schema(description = "Latitude of the stop", example = "40.7128")
    private Double latitude;

    @Schema(description = "Longitude of the stop", example = "-74.0060")
    private Double longitude;
}
