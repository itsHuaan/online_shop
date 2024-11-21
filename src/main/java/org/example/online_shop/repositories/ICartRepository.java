package org.example.online_shop.repositories;

import org.example.online_shop.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<CartEntity, Long>, JpaSpecificationExecutor<CartEntity> {
    Optional<CartEntity> findByUser_UserId(Long userId);
}
