package org.example.online_shop.mappers;

import java.io.IOException;

public interface IBaseMapper<T, U, V> {
    T toDTO(V entity);

    V toEntity(U model) throws IOException;
}
