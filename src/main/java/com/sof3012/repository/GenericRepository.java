package com.sof3012.repository;

import java.util.List;

public interface GenericRepository<T, E> {

    T create(T entity);

    T update(T entity);

    T delete(T entity);

    T findById(E idEntity);

    int countSize();

    List<T> findAll(boolean existIsActive);

    List<T> findAll(boolean existIsActive, int pageNumber, int pageSize);
}
