package com.gtu.route_management_service.application.mapper;

import com.gtu.route_management_service.application.dto.StopDTO;
import com.gtu.route_management_service.domain.model.Stop;

import lombok.experimental.UtilityClass;

import java.util.List;


@UtilityClass
public class StopMapper {



    public StopDTO toDTO(Stop domain) {
        return (domain == null) ? null : new StopDTO(
            domain.getId(),
            domain.getName(),
            domain.getDescription(),
            domain.getNeighborhoodId(), 
            domain.getLatitude(),
            domain.getLongitude()
        );
    }

    public Stop toDomain(StopDTO dto) {
        return (dto == null) ? null : new Stop(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getNeighborhoodId(),
            dto.getLatitude(),
            dto.getLongitude()
        );
    }

    public List<StopDTO> toDTOList(List<Stop> domainList) {
        return domainList == null ? List.of() : domainList.stream().map(StopMapper::toDTO).toList();
    }

    public List<Stop> toDomainList(List<StopDTO> dtoList) {
        return dtoList == null ? List.of() : dtoList.stream().map(StopMapper::toDomain).toList();
    }
}
