package com.gtu.route_management_service.domain.model;

import java.time.LocalTime;
import java.util.List;

public class Route {
    private Long id;
    private String name;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Long> neighborhoodIds;
    private List<Long> stopsIds;

    public Route(){

    }

    public Route(String name, String description, LocalTime startTime, LocalTime endTime, List<Long> neighborhoodIds, List<Long> stopIds) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.neighborhoodIds = neighborhoodIds;
        this.stopsIds = stopIds;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public LocalTime getEndTime(){
        return endTime;
    }

    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }

    public List<Long> getNeighborhoodIds(){
        return neighborhoodIds;
    }

    public void setNeighborhoodIds(List<Long> neighborhoodIds){
        this.neighborhoodIds = neighborhoodIds;
    }

    public List<Long> getStopsIds(){
        return stopsIds;
    }

    public void setStop(List<Long> stopIds){
        this.stopsIds = stopIds;
    }

    
}
