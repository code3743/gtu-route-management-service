package com.gtu.route_management_service.infrastructure.mappers;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.infrastructure.entities.StopEntity;

import java.util.List;

public class StopMapper {

    private StopMapper() {
    }

    public static Stop toDomain(StopEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Stop(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            NeighborhoodMapper.toDomain(entity.getNeighborhood()), 
            entity.getLatitude(),
            entity.getLongitude()
        );
    }

    public static StopEntity toEntity(Stop domain) {
        if (domain == null) {
            return null;
        }
        StopEntity entity = new StopEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setNeighborhood(NeighborhoodMapper.toEntity(domain.getNeighborhood())); 
        entity.setLatitude(domain.getLatitude());
        entity.setLongitude(domain.getLongitude());
        return entity;
    }

    public static List<Stop> toDomainList(List<StopEntity> entities) {
        return entities.stream().map(StopMapper::toDomain).toList();
    }

    public static List<StopEntity> toEntityList(List<Stop> domains) {
        return domains.stream().map(StopMapper::toEntity).toList();
    }
}
