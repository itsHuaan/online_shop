package org.example.online_shop.services;

import org.example.online_shop.dto.UserDto;
import org.example.online_shop.models.UserModel;

public interface IUserService extends IBaseService<UserDto, UserModel, Long> {
    UserDto findByUsername(String username);
    UserDto findByEmailOrUsername(String email, String username);
}
