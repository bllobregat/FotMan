package com.example.version.service;

import com.example.version.model.User;

import java.util.List;

public interface BaseService<T, K> {

    List<T> listALL();

    void update(K id, T object);

    User save(T object);

    T get(K id);

    void delete(K id);
}

