package com.gtu.route_management_service.infrastructure.persistence;

import com.gtu.route_management_service.domain.repository.StopRepository;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryStopRepository implements StopRepository {
    private final List<Long> existingStopIds = Arrays.asList(1L, 2L, 3L);

    @Override
    public boolean existsById(Long id) {
        return existingStopIds.contains(id);
    }

    @Override
    public List<Long> findAllExistingIds(List<Long> ids) {
        return ids.stream()
            .filter(this::existsById)
            .collect(Collectors.toList());
    }
}
