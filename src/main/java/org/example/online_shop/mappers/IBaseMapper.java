package org.example.online_shop.mappers;

public interface IBaseMapper<T, U, V> {
    T toDTO(V entity);

    V toEntity(U model);
}
