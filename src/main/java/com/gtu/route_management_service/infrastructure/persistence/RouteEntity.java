package com.gtu.route_management_service.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "routes")
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalTime starTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ElementCollection
    @CollectionTable(name = "route_neighborhoods", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "neighborhood")
    private List<String> neighborhoods;

    @ElementCollection
    @CollectionTable(name = "route_stops", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "stop_id")
    private List<Long> stopIds;

    public RouteEntity(){

    }

    public RouteEntity(String name, String description, LocalTime startTime, LocalTime endTime, List<String> neighborhoods, List<Long> stopIds) {
        this.name = name;
        this.description = description;
        this.starTime = startTime;
        this.endTime = endTime;
        this.neighborhoods = neighborhoods;
        this.stopIds = stopIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
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

    public LocalTime getStarTime() {
        return starTime;
    }

    public void setStarTime(LocalTime starTime) {
        this.starTime = starTime;
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
