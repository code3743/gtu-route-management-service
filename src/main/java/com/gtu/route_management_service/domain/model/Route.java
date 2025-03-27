package com.gtu.route_management_service.domain.model;

import java.time.LocalTime;
import java.util.List;

public class Route {
    private Long id;
    private String name;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> neighborhoods;
    private List<Long> stopIds;

    public Route(){

    }

    public Route(String name, LocalTime startTime, LocalTime endTime, List<String> neighborhoods, List<Long> stopIds) {
        this.name = name;
        this.description = null;
        this.startTime = startTime;
        this.endTime = endTime;
        this.neighborhoods = neighborhoods;
        this.stopIds = stopIds;
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

    public List<String> getNeighborhoods(){
        return neighborhoods;
    }

    public void setNeighborhoods(List<String> neighborhoods){
        this.neighborhoods = neighborhoods;
    }

    public List<Long> getStopIds(){
        return stopIds;
    }

    public void setStopIds(List<Long> stopIds){
        this.stopIds = stopIds;
    }

    
}
