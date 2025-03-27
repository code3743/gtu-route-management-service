package com.gtu.route_management_service.infrastructure.mappers;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.infrastructure.entities.NeighborhoodEntity;

import java.util.List;

public class NeighborhoodMapper {

    private NeighborhoodMapper() {
    }


    public static Neighborhood toDomain(NeighborhoodEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Neighborhood(entity.getId(), entity.getName());
    }

    public static NeighborhoodEntity toEntity(Neighborhood domain) {
        if (domain == null) {
            return null;
        }
        NeighborhoodEntity entity = new NeighborhoodEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        return entity;
    }

    public static List<Neighborhood> toDomainList(List<NeighborhoodEntity> entities) {
        return entities.stream().map(NeighborhoodMapper::toDomain).toList();
    }

    public static List<NeighborhoodEntity> toEntityList(List<Neighborhood> domains) {
        return domains.stream().map(NeighborhoodMapper::toEntity).toList();
    }
}
