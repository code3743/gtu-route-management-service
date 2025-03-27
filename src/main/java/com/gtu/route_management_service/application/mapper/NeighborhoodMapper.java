package com.gtu.route_management_service.application.mapper;

import java.util.List;

import com.gtu.route_management_service.application.dto.NeighborhoodDTO;
import com.gtu.route_management_service.domain.model.Neighborhood;

public class NeighborhoodMapper {

    private NeighborhoodMapper() {
    }

    public static NeighborhoodDTO toDTO(Neighborhood domain) {
        return (domain == null) ? null : new NeighborhoodDTO(domain.getId(), domain.getName());
    }

    public static Neighborhood toDomain(NeighborhoodDTO dto) {
        return (dto == null) ? null : new Neighborhood(dto.getId(), dto.getName());
    }

    public static List<NeighborhoodDTO> toDTOList(List<Neighborhood> domainList) {
        return domainList == null ? List.of() : domainList.stream().map(NeighborhoodMapper::toDTO).toList();
    }

    public static List<Neighborhood> toDomainList(List<NeighborhoodDTO> dtoList) {
        return dtoList == null ? List.of() : dtoList.stream().map(NeighborhoodMapper::toDomain).toList();
    }
}