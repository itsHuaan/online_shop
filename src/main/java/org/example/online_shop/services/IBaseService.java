package org.example.online_shop.services;

import java.util.List;

public interface IBaseService<T, U, K> {
    List<T> findAll();
    T findById(K id);
    T save(U entity);
    int delete(K id);
}
