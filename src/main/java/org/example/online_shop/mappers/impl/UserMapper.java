package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.UserDto;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IBaseMapper<UserDto, UserModel, UserEntity> {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto toDTO(UserEntity entity) {
        return UserDto.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .name(entity.getName())
                .phone(entity.getPhone())
                .build();
    }

    @Override
    public UserEntity toEntity(UserModel model) {
        return UserEntity.builder()
                .userId(model.getId())
                .name(model.getName())
                .username(model.getUsername())
                .email(model.getEmail())
                .password(passwordEncoder.encode(model.getPassword()))
                .address(model.getAddress())
                .phone(model.getPhone())
                .build();
    }
}
