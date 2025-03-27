package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.infrastructure.persistence.entities.StopEntity;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;


@UtilityClass
public class StopMapper {

    public Stop toDomain(StopEntity entity) {
        return (entity == null) ? null : new Stop(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            NeighborhoodMapper.toDomain(entity.getNeighborhood()), 
            entity.getLatitude(),
            entity.getLongitude()
        );
    }

    public StopEntity toEntity(Stop domain) {
        if (domain == null) {
            return null;
        }
        return new StopEntity(
            domain.getName(),
            domain.getDescription(),
            NeighborhoodMapper.toEntity(domain.getNeighborhood()), 
            domain.getLatitude(),
            domain.getLongitude()
        ); 
    }

    public List<Stop> toDomainList(List<StopEntity> entities) {
        return (entities == null) ? Collections.emptyList() 
                                  : entities.stream().map(StopMapper::toDomain).toList();
    }

    public List<StopEntity> toEntityList(List<Stop> domains) {
        return (domains == null) ? Collections.emptyList() 
                                 : domains.stream().map(StopMapper::toEntity).toList();
    }
}
