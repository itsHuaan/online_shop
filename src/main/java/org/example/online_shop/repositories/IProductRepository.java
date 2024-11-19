package org.example.online_shop.repositories;

import org.example.online_shop.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public interface IProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
Optional<ProductEntity> findByNameAndPublishDate(String name, Integer publishDate);
}
