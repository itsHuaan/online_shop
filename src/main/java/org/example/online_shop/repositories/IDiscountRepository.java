package org.example.online_shop.repositories;

import org.example.online_shop.entities.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDiscountRepository extends JpaRepository<DiscountEntity, Long>, JpaSpecificationExecutor<DiscountEntity> {
}
