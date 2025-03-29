package com.gtu.route_management_service.domain.repository;

import java.util.List;
import java.util.Optional;

import com.gtu.route_management_service.domain.vo.PaginatedResult;

public interface GenericRepository<T, I> {

    T save(T entity);
    boolean existsById(I id);
    List<T> findAll();
    Optional<T> findById(I id);
    List<T> findAllById(List<I> ids);
    void deleteById(I id);
    T update(T entity);
    List<I> findAllExistingIds(List<I> ids);

    PaginatedResult<T> findAll(int page, int size);
    List<T> searchByFieldLike(String fieldName, String value);
    PaginatedResult<T> searchByFieldLike(String fieldName, String value, int page, int size);
 
    long countAll();

    List<T> saveAll(List<T> entities);
    void deleteAllById(List<I> ids);
}
