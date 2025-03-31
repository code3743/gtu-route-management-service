package com.gtu.route_management_service.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO that represents a neighborhood in the transportation system")
public class NeighborhoodDTO {

    @Schema(description = "Unique identifier of the neighborhood", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Name of the neighborhood", example = "Downtown")
    private String name;
}
