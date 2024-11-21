package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.CartDetailDto;
import org.example.online_shop.entities.CartDetailEntity;
import org.example.online_shop.entities.CartEntity;
import org.example.online_shop.entities.ProductEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.CartDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDetailMapper implements IBaseMapper<CartDetailDto, CartDetailModel, CartDetailEntity> {
    @Autowired
    ProductMapper productMapper;

    @Override
    public CartDetailDto toDTO(CartDetailEntity entity) {
        return CartDetailDto.builder()
                .cartId(entity.getCart().getCartId())
                .productDto(productMapper.toDTO(entity.getProduct()))
                .quantity(entity.getQuantity())
                .build();
    }

    @Override
    public CartDetailEntity toEntity(CartDetailModel model) {
        CartEntity cart = new CartEntity();
        ProductEntity product = new ProductEntity();
        cart.setCartId(model.getCartId());
        product.setProductId(model.getProductId());
        return CartDetailEntity.builder()
                .cart(cart)
                .product(product)
                .quantity(model.getQuantity())
                .build();
    }
}
