package org.example.online_shop.services;

import org.example.online_shop.dto.CartDto;
import org.example.online_shop.models.CartModel;

public interface ICartService extends IBaseService<CartDto, CartModel, Long> {
    CartDto getCartDto(Long id);
}
