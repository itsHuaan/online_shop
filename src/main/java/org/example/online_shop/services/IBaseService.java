package org.example.online_shop.services;

import java.io.IOException;
import java.util.List;

public interface IBaseService<T, U, K> {
    List<T> findAll();
    T findById(K id);
    int save(U u) throws IOException;
    int delete(K id);
}
