package org.example.online_shop.services.impl;

import org.example.online_shop.dto.CartDto;
import org.example.online_shop.entities.CartEntity;
import org.example.online_shop.mappers.impl.CartMapper;
import org.example.online_shop.models.CartModel;
import org.example.online_shop.repositories.ICartRepository;
import org.example.online_shop.repositories.IUserRepository;
import org.example.online_shop.services.ICartService;
import org.example.online_shop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService implements ICartService {
    private final ICartRepository cartRepository;
    private final IUserRepository userRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartService(ICartRepository cartRepository, IUserRepository userRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public List<CartDto> findAll() {
        return List.of();
    }

    @Override
    public CartDto findById(Long id) {
        return null;
    }

    @Override
    public int save(CartModel cartModel) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public CartDto getCartDto(Long id) {
        return cartMapper.toDTO(cartRepository.findByUser_UserId(id).orElseGet(() -> {
            CartEntity newCart = new CartEntity();
            newCart.setUser(userRepository.findById(id).get());
            newCart.setCreatedDate(LocalDateTime.now());
            return cartRepository.save(newCart);
        }));
    }
}
