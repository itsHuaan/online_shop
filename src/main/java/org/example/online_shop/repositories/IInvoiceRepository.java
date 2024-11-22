package org.example.online_shop.repositories;

import org.example.online_shop.entities.DiscountEntity;
import org.example.online_shop.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IInvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {
}
