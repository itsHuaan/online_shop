package org.example.online_shop.services;

import org.example.online_shop.dto.UserDto;
import org.example.online_shop.models.SignUpUserRequest;
import org.example.online_shop.models.UserModel;

import java.util.List;

public interface IUserService extends IBaseService<UserDto, UserModel, Long> {
    UserDto findByUsername(String username);
    UserDto findByEmailOrUsername(String email, String username);
    List<UserDto> findByRoleId(Long roleId);
    int checkCodeAndSave(SignUpUserRequest signUpUserRequest);
}
