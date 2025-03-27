package com.gtu.route_management_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "routes")
@Getter
@NoArgsConstructor
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

    @ManyToMany
    @JoinTable(name = "route_neighborhoods", joinColumns = @JoinColumn(name = "route_id"), inverseJoinColumns = @JoinColumn(name = "neighborhood_id"))
    @Column(name = "neighborhood_id")
    private List<NeighborhoodEntity> neighborhood;

    @ManyToMany
    @JoinTable(name = "route_stops", joinColumns = @JoinColumn(name = "route_id"), inverseJoinColumns = @JoinColumn(name = "stop_id"))
    private List<StopEntity> stop;

    public RouteEntity(String name, String description, LocalTime startTime, LocalTime endTime, List<NeighborhoodEntity> neighborhoods, List<StopEntity> stopIds) {
        this.name = name;
        this.description = description;
        this.starTime = startTime;
        this.endTime = endTime;
        this.neighborhood = neighborhoods;
        this.stop = stopIds;
    }
}
