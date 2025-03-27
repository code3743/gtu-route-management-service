package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.StopRepository;
import com.gtu.route_management_service.infrastructure.entities.StopEntity;
import com.gtu.route_management_service.infrastructure.mappers.StopMapper;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

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
            .map(StopEntity::getId).toList();
    }

    @Override
    public Optional<Stop> findById(Long id) {
        return jpaStopRepository.findById(id)
            .map(StopMapper::toDomain);
    }

    @Override
    public Stop save(Stop stop) {
        StopEntity stopEntity = StopMapper.toEntity(stop);
        StopEntity savedEntity = jpaStopRepository.save(stopEntity);
        return StopMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        jpaStopRepository.deleteById(id);
    }

    @Override
    public List<Stop> findAll() {
        return jpaStopRepository.findAll().stream()
            .map(StopMapper::toDomain).toList();
    }

    @Override
    public List<Stop> findAllById(List<Long> ids) {
        return jpaStopRepository.findAllById(ids).stream()
            .map(StopMapper::toDomain).toList();
    }
}
