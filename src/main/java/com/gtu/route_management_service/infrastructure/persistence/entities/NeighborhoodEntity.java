package com.gtu.route_management_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "neighborhoods")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public NeighborhoodEntity(Long id) {
        this.id = id;
    }
}
