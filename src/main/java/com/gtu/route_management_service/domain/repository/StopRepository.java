package com.gtu.route_management_service.domain.repository;

import java.util.List;

public interface StopRepository {
    boolean existsById(Long id);
    List<Long> findAllExistingIds(List<Long> ids);
}