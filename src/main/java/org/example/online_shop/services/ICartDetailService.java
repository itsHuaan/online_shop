package org.example.online_shop.services;

import org.example.online_shop.dto.CartDetailDto;
import org.example.online_shop.models.CartDetailModel;

public interface ICartDetailService extends IBaseService<CartDetailDto, CartDetailModel, Long> {
    CartDetailDto getCartDetail(Long cartId, Long productId);
    void updateQuantity(Long cartId, int quantity);
}
