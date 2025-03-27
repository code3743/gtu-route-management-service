package com.gtu.route_management_service.infrastructure.persistence.entities;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "neighborhood_id", nullable = false)
    private NeighborhoodEntity neighborhood;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    public StopEntity(String name, String description, NeighborhoodEntity neighborhood, Double latitude, Double longitude) {
        this.name = name;
        this.description = description;
        this.neighborhood = neighborhood;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
