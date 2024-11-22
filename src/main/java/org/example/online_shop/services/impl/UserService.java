package org.example.online_shop.services.impl;

import org.example.online_shop.configurations.UserDetailsImpl;
import org.example.online_shop.dto.UserDto;
import org.example.online_shop.entities.OtpEntity;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.impl.UserMapper;
import org.example.online_shop.models.SignUpUserRequest;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.repositories.IUserRepository;
import org.example.online_shop.services.IUserService;
import org.example.online_shop.utils.specifications.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final OtpService otpService;

    @Autowired
    public UserService(IUserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, OtpService otpService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll(Specification.where(UserSpecifications.isActive())).stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDTO(Objects.requireNonNull(userRepository.findById(id).orElse(null)));
    }

    @Override
    public int save(UserModel model) {
        UserEntity currentUser = model.getUserId() != null
                ? userRepository.findById(model.getUserId()).orElse(null)
                : null;
        if (currentUser != null) {
            userRepository.save(mapNonNullFieldsToEntity(model, currentUser));
            return 2;
        } else {
            model.setPassword(passwordEncoder.encode(model.getPassword()));
            userRepository.save(userMapper.toEntity(model));
            return 1;
        }
    }

    @Override
    public int delete(Long id) {
        UserEntity currentUser = userRepository.findById(id).orElse(null);
        if (currentUser != null) {
            currentUser.setStatus(false);
            userRepository.save(currentUser);
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
    public List<UserDto> findByRoleId(Long roleId) {
        return userRepository.findAll(Specification.where(UserSpecifications.hasRole(roleId)).and(UserSpecifications.isActive())).stream().map(userMapper::toDTO).toList();
    }

    private UserEntity mapNonNullFieldsToEntity(UserModel userModel, UserEntity userEntity) {
        for (Field userField : userModel.getClass().getDeclaredFields()) {
            try {
                Field dtoField = userEntity.getClass().getDeclaredField(userField.getName());
                userField.setAccessible(true);
                Object value = userField.get(userModel);

                if (value != null) {
                    dtoField.setAccessible(true);
                    dtoField.set(userEntity, value);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field missing in ProfileDto: " + userField.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return userEntity;
    }

    public int checkCodeAndSave(SignUpUserRequest signUpUserRequest){
        boolean check = otpService.isExpired(signUpUserRequest.getEmail());
        if (!check){
            return 2;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(signUpUserRequest.getName());
        userEntity.setUsername(signUpUserRequest.getUsername());
        userEntity.setEmail(signUpUserRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(signUpUserRequest.getPassword()));
        userEntity.setPhone(signUpUserRequest.getPhone());
        userEntity.setAddress(signUpUserRequest.getAddress());
        try {
            userRepository.save(userEntity);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }
}
