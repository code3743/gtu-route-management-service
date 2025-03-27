package com.gtu.route_management_service.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.List;

public class RouteDTO {
    @NotEmpty(message = "The route name cannot be empty")
    private String name;

    private String description;

    @NotNull(message = "The start time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "The end time is mandatory")
    private LocalTime endTime;

    @NotEmpty(message = "The list of neighbothoods cannot be empty")
    private List<String> neighborhoods;

    @NotEmpty(message = "The stop list cannot be empty")
    @Size(min = 2, message = "The route must have at least two stops")
    private List<Long> stopIds;

    public RouteDTO(){

    }

    public RouteDTO(String name, String description, LocalTime starTime, LocalTime endTime, List<String> neighborhoods, List<Long> stopIds){
        this.name = name;
        this.description = description;
        this.startTime = starTime;
        this.endTime = endTime;
        this.neighborhoods = neighborhoods;
        this.stopIds = stopIds;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<String> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public List<Long> getStopIds() {
        return stopIds;
    }

    public void setStopIds(List<Long> stopIds) {
        this.stopIds = stopIds;
    }

}
