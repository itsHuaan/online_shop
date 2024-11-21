package org.example.online_shop.services.impl;

import org.example.online_shop.dto.CartDetailDto;
import org.example.online_shop.entities.CartDetailEntity;
import org.example.online_shop.mappers.impl.CartDetailMapper;
import org.example.online_shop.models.CartDetailModel;
import org.example.online_shop.repositories.ICartDetailRepository;
import org.example.online_shop.repositories.ICartRepository;
import org.example.online_shop.repositories.IProductRepository;
import org.example.online_shop.services.ICartDetailService;
import org.example.online_shop.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartDetailService implements ICartDetailService {
    private final ICartDetailRepository cartDetailRepository;
    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;
    private final CartDetailMapper cartDetailMapper;

    @Autowired
    public CartDetailService(ICartDetailRepository cartDetailRepository, ICartRepository cartRepository, IProductRepository productRepository, CartDetailMapper cartDetailMapper) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartDetailMapper = cartDetailMapper;
    }

    @Override
    public List<CartDetailDto> findAll() {
        return List.of();
    }

    @Override
    public CartDetailDto findById(Long id) {
        return null;
    }

    @Override
    public int save(CartDetailModel cartDetailModel) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public CartDetailDto getCartDetail(Long cartId, Long productId) {
        return cartDetailMapper.toDTO(cartDetailRepository.findByCart_CartIdAndProduct_ProductId(cartId, productId).orElseGet(()->{
            CartDetailEntity newCartDetail = new CartDetailEntity();
            newCartDetail.setCart(cartRepository.findById(cartId).get());
            newCartDetail.setProduct(productRepository.findById(productId).get());
            newCartDetail.setQuantity(0);
            newCartDetail.setCreatedDate(LocalDateTime.now());
            return cartDetailRepository.save(newCartDetail);
        }));
    }

    @Override
    public void updateQuantity(Long cartId, int quantity) {
        CartDetailEntity currentCart = cartDetailRepository.findById(cartId).orElse(null);
        if (currentCart != null) {
            currentCart.setQuantity(currentCart.getQuantity() + quantity);
            cartDetailRepository.save(currentCart);
        }
    }

}
