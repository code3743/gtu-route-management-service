package com.gtu.route_management_service.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import com.gtu.route_management_service.infrastructure.persistence.entities.NeighborhoodEntity;
import com.gtu.route_management_service.infrastructure.persistence.mappers.NeighborhoodMapper;

@Repository
public class NeighborhoodRepositoryImpl implements NeighborhoodRepository{

    private final JpaNeighborhoodRepository jpaNeighborhoodRepository;

    public NeighborhoodRepositoryImpl(JpaNeighborhoodRepository jpaNeighborhoodRepository) {
        this.jpaNeighborhoodRepository = jpaNeighborhoodRepository;
    }

    @Override
    public Neighborhood save(Neighborhood neighborhood) {
        NeighborhoodEntity neighborhoodEntity =  NeighborhoodMapper.toEntity(neighborhood);
        return NeighborhoodMapper.toDomain(jpaNeighborhoodRepository.save(neighborhoodEntity));
    }

    @Override
    public List<Neighborhood> findAll() {
       return NeighborhoodMapper.toDomainList(jpaNeighborhoodRepository.findAll());
    }

    @Override
    public Optional<Neighborhood> findAllById(Long id) {
        return jpaNeighborhoodRepository.findById(id).map(NeighborhoodMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaNeighborhoodRepository.deleteById(id);
    }

    @Override
    public Neighborhood update(Neighborhood neighborhood) {
       throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public boolean existsById(Long id) {
        return jpaNeighborhoodRepository.existsById(id);
    }

}
