package com.gtu.route_management_service.application.service;

import com.gtu.route_management_service.domain.model.Stop;
import com.gtu.route_management_service.domain.repository.NeighborhoodRepository;
import com.gtu.route_management_service.domain.repository.StopRepository;
import com.gtu.route_management_service.domain.service.StopService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StopServiceImpl implements StopService {
    private final StopRepository stopRepository;
    private final NeighborhoodRepository neighborhoodRepository;


    public StopServiceImpl(StopRepository stopRepository, NeighborhoodRepository neighborhoodRepository) {
        this.stopRepository = stopRepository;
        this.neighborhoodRepository = neighborhoodRepository;
    }

    @Override
    public Stop createStop(Stop stop) {
        if (stop.getName() == null || stop.getName().isEmpty()) {
            throw new IllegalArgumentException("Stop name cannot be empty");
        }
        if (!neighborhoodRepository.existsById(stop.getNeighborhoodId())) {
            throw new IllegalArgumentException("Neighborhood does not exist");
        }
        return stopRepository.save(stop);
    }

    @Override
    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    @Override
    public Optional<Stop> getStopById(Long id) {
        return stopRepository.findById(id);
    }

    @Override
    public void deleteStop(Long id) {
        stopRepository.deleteById(id);
    }

    @Override
    public Stop updateStop(Stop stop) {
        if (!stopRepository.existsById(stop.getId())) {
            throw new IllegalArgumentException("Stop does not exist");
        }

        if (!neighborhoodRepository.existsById(stop.getNeighborhoodId())) {
            throw new IllegalArgumentException("Neighborhood does not exist");
        }
        return stopRepository.update(stop);
    }

    @Override
    public List<Stop> searchByName(String name) {
        return stopRepository.searchByName(name);
    }
}
