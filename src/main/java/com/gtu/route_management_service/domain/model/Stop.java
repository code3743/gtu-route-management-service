package com.gtu.route_management_service.domain.model;

import java.util.Objects;

public class Stop {
    private final Long id;
    private final String name;
    private final String description;
    private final Neighborhood neighborhood;
    private final Double latitude;
    private final Double longitude;

    public Stop(Long id, String name, String description, Neighborhood neighborhood, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.neighborhood = neighborhood;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Stop(String name, String description, Neighborhood neighborhood, Double latitude, Double longitude) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.neighborhood = neighborhood;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return Objects.equals(id, stop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Stop{id=" + id + ", name='" + name + "', neighborhood=" + neighborhood.getName() + "}";
    }
}
