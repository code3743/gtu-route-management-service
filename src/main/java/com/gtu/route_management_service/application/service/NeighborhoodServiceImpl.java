package com.gtu.route_management_service.application.service;

import com.gtu.route_management_service.domain.model.Neighborhood;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import com.gtu.route_management_service.domain.service.NeighborhoodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {
    private final NeighborhoodRepository neighborhoodRepository;

    public NeighborhoodServiceImpl(NeighborhoodRepository neighborhoodRepository) {
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public Neighborhood createNeighborhood(Neighborhood neighborhood) {
        if (neighborhood.getName() == null || neighborhood.getName().isEmpty()) {
            throw new IllegalArgumentException("Neighborhood name cannot be empty");
        }
        return neighborhoodRepository.save(neighborhood);
    }

    @Override
    public List<Neighborhood> getAllNeighborhoods() {
        return neighborhoodRepository.findAll();
    }

    @Override
    public List<Neighborhood> getNeighborhoodsByIds(List<Long> id) {
        return neighborhoodRepository.findAllById(id);
    }

    @Override
    public void deleteNeighborhood(Long id) {
        neighborhoodRepository.deleteById(id);
    }

    @Override
    public Optional<Neighborhood> getNeighborhoodById(Long id) {
        return neighborhoodRepository.findById(id);
    }

    @Override
    public Neighborhood updateNeighborhood(Neighborhood neighborhood) {
        if (!neighborhoodRepository.existsById(neighborhood.getId())) {
            throw new IllegalArgumentException("Neighborhood does not exist");
        }
        if (neighborhood.getName() == null || neighborhood.getName().isEmpty()) {
            throw new IllegalArgumentException("Neighborhood name cannot be empty");
        }
        return neighborhoodRepository.update(neighborhood);
    }

    @Override
    public List<Neighborhood> searchByName(String name) {
        return neighborhoodRepository.searchByName(name);
    }
}
