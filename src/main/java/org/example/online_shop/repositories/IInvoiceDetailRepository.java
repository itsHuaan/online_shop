package org.example.online_shop.repositories;

import org.example.online_shop.entities.InvoiceDetailEntity;
import org.example.online_shop.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IInvoiceDetailRepository extends JpaRepository<InvoiceDetailEntity, Long>, JpaSpecificationExecutor<InvoiceDetailEntity> {
}
