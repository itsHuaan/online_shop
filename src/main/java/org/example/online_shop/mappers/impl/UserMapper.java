package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.UserDto;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IBaseMapper<UserDto, UserModel, UserEntity> {
    @Override
    public UserDto toDTO(UserEntity entity) {
        return UserDto.builder().build();
    }

    @Override
    public UserEntity toEntity(UserModel model) {
        return UserEntity.builder().build();
    }
}
