package com.gtu.route_management_service.infrastructure.persistence.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "stops")
@Getter
@NoArgsConstructor 
@AllArgsConstructor 
public class StopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(name = "neighborhood_id", nullable = false)
    private Long neighborhoodId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public StopEntity(String name, String description, Long neighborhoodId, Double latitude, Double longitude) {
        this.name = name;
        this.description = description;
        this.neighborhoodId = neighborhoodId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public StopEntity(Long id) {
        this.id = id;
    }
}
