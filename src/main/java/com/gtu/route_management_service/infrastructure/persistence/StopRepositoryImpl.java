package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.repository.StopRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StopRepositoryImpl implements StopRepository {
    private final JpaStopRepository jpaStopRepository;

    public StopRepositoryImpl(JpaStopRepository jpaStopRepository) {
        this.jpaStopRepository = jpaStopRepository;
    }

    @Override
    public boolean existsById(Long id) {
        return jpaStopRepository.existsById(id);
    }

    @Override
    public List<Long> findAllExistingIds(List<Long> ids) {
        return jpaStopRepository.findAllById(ids).stream()
            .map(StopEntity::getId)
            .collect(Collectors.toList());
    }
}
