package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.CartDto;
import org.example.online_shop.entities.CartEntity;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.CartDetailModel;
import org.example.online_shop.models.CartModel;
import org.example.online_shop.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartMapper implements IBaseMapper<CartDto, CartModel, CartEntity> {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CartDetailMapper cartDetailMapper;

    @Override
    public CartDto toDTO(CartEntity entity) {
        return CartDto.builder()
                .cartId(entity.getCartId())
                .user(userMapper.toDTO(entity.getUser()))
                .cartDetailDtoList(entity.getCartDetails().stream().map(cartDetailMapper::toDTO).toList())
                .build();
    }

    @Override
    public CartEntity toEntity(CartModel model) {
        UserEntity user = new UserEntity();
        user.setUserId(model.getUserId());
        return CartEntity.builder()
                .user(user)
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
