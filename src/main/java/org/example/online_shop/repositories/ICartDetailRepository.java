package org.example.online_shop.repositories;

import org.example.online_shop.entities.CartDetailEntity;
import org.example.online_shop.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ICartDetailRepository extends JpaRepository<CartDetailEntity, Long>, JpaSpecificationExecutor<CartDetailEntity> {
    Optional<CartDetailEntity> findByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);
}
