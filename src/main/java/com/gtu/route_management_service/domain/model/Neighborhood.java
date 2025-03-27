package com.gtu.route_management_service.domain.model;

import java.util.Objects;

public class Neighborhood {

    private final Long id;
    private final String name;

    public Neighborhood(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Neighborhood(String name) {
        this.id = null;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighborhood that = (Neighborhood) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Neighborhood{id=" + id + ", name='" + name + "'}";
    }
}
