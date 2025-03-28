package com.gtu.route_management_service.infrastructure.persistence.mappers;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;

import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;


@UtilityClass
public class NeighborhoodMapper {

    public Neighborhood toDomain(NeighborhoodEntity entity) {
        return (entity == null) ? null : new Neighborhood(entity.getId(), entity.getName());
    }

    public NeighborhoodEntity toEntity(Neighborhood domain) {
        return (domain == null) ? null : new NeighborhoodEntity(domain.getId(),domain.getName()); 
    }

    public List<Neighborhood> toDomainList(List<NeighborhoodEntity> entities) {
        return (entities == null) ? Collections.emptyList() 
                                  : entities.stream().map(NeighborhoodMapper::toDomain).toList();
    }

    public List<NeighborhoodEntity> toEntityList(List<Neighborhood> domains) {
        return (domains == null) ? Collections.emptyList() 
                                 : domains.stream().map(NeighborhoodMapper::toEntity).toList();
    }
}
