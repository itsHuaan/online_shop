package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.UserDto;
import org.example.online_shop.entities.RoleEntity;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IBaseMapper<UserDto, UserModel, UserEntity> {
    @Override
    public UserDto toDTO(UserEntity entity) {
        return UserDto.builder()
                .userId(entity.getUserId())
                .username(entity.getUsername())
//                .password(entity.getPassword())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .name(entity.getName())
                .phone(entity.getPhone())
                .status(entity.getStatus())
                .roleId(entity.getRole().getRoleId())
                .build();
    }

    @Override
    public UserEntity toEntity(UserModel model) {
        RoleEntity role = new RoleEntity();
        role.setRoleId(model.getRoleId());
        return UserEntity.builder()
                .userId(model.getUserId())
                .name(model.getName())
                .username(model.getUsername())
                .email(model.getEmail())
                .password(model.getPassword())
                .address(model.getAddress())
                .phone(model.getPhone())
                .role(role)
                .createdDate(model.getCreatedDate())
                .profilePicture(model.getProfilePicture())
                .status(model.getStatus())
                .build();
    }
}
