package org.example.online_shop.services.impl;

import org.example.online_shop.dto.UserDto;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.services.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Override
    public List<UserDto> findAll() {
        return List.of();
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public UserDto save(UserModel entity) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public UserDto findByUsername(String username) {
        return null;
    }
}
