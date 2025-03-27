package com.gtu.route_management_service.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StopDTO {
    private Long id;
    private String name;
    private String description;
    private Long neighborhoodId; 
    private Double latitude;
    private Double longitude;
}
