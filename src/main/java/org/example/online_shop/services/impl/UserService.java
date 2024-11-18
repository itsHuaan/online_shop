package org.example.online_shop.services.impl;

import org.example.online_shop.configurations.UserDetailsImpl;
import org.example.online_shop.dto.UserDto;
import org.example.online_shop.mappers.impl.UserMapper;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.repositories.IUserRepository;
import org.example.online_shop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(IUserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDTO(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    @Override
    public UserDto save(UserModel model) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(model)));
    }

    @Override
    public int delete(Long id) {
        try {
            userRepository.deleteById(id);
            return 1;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username));
    }

    @Override
    public UserDto findByEmailOrUsername(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username)
                .map(userMapper::toDTO)
                .orElse(null);
    }
}
