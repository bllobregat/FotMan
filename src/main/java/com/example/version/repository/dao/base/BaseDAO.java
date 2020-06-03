package com.example.version.repository.dao.base;

import java.util.List;

public interface BaseDAO<T, K> {

    void save(T object);

    void update(K id, T object);

    boolean deleteById(K id);

    boolean deleteAll();

    List<T> findAll();

    T findById(K id);

}
