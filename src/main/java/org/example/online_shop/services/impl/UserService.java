package org.example.online_shop.services.impl;

import org.example.online_shop.configurations.UserDetailsImpl;
import org.example.online_shop.dto.UserDto;
import org.example.online_shop.mappers.impl.UserMapper;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.repositories.IUserRepository;
import org.example.online_shop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

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
    public int save(UserModel model) {
        userRepository.save(userMapper.toEntity(model));
        return 1;
    }

    @Override
    public int delete(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return 1;
        }
        return 0;
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

    @Override
    public UserModel mapNonNullFields(UserDto userDto, UserModel userModel) {
        for (Field f : userModel.getClass().getDeclaredFields()) {
            try {
                Field dtoField = userDto.getClass().getDeclaredField(f.getName());
                f.setAccessible(true);
                if (f.get(userModel) == null) {
                    dtoField.setAccessible(true);
                    Object value = dtoField.get(userDto);
                    if (value != null) {
                        f.set(userModel, value);
                    }
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field missing in ProfileDto: " + f.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return userModel;
    }
}
