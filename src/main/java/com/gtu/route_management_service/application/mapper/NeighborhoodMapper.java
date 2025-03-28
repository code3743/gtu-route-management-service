package com.gtu.route_management_service.application.mapper;

import java.util.List;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.domain.model.Neighborhood;

import lombok.experimental.UtilityClass;

@UtilityClass
public class NeighborhoodMapper {


    public NeighborhoodDTO toDTO(Neighborhood domain) {
        return (domain == null) ? null : new NeighborhoodDTO(domain.getId(), domain.getName());
    }

    public Neighborhood toDomain(NeighborhoodDTO dto) {
        return (dto == null) ? null : new Neighborhood(dto.getId(), dto.getName());
    }

    public List<NeighborhoodDTO> toDTOList(List<Neighborhood> domainList) {
        return domainList == null ? List.of() : domainList.stream().map(NeighborhoodMapper::toDTO).toList();
    }

    public List<Neighborhood> toDomainList(List<NeighborhoodDTO> dtoList) {
        return dtoList == null ? List.of() : dtoList.stream().map(NeighborhoodMapper::toDomain).toList();
    }
}