package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.InvoiceDetailDto;
import org.example.online_shop.dto.InvoiceDto;
import org.example.online_shop.entities.InvoiceDetailEntity;
import org.example.online_shop.entities.InvoiceEntity;
import org.example.online_shop.entities.ProductEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.InvoiceDetailModel;
import org.example.online_shop.models.InvoiceModel;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDetailMapper implements IBaseMapper<InvoiceDetailDto, InvoiceDetailModel, InvoiceDetailEntity> {
    @Override
    public InvoiceDetailDto toDTO(InvoiceDetailEntity entity) {
        return InvoiceDetailDto.builder()
                .invoiceDetailId(entity.getInvoiceDetailId())
                .productId(entity.getProduct().getProductId())
                .productName(entity.getProduct().getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .status(entity.getStatus())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    @Override
    public InvoiceDetailEntity toEntity(InvoiceDetailModel model) {
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setInvoiceId(model.getInvoiceId());
        ProductEntity product = new ProductEntity();
        product.setProductId(model.getProductId());
        return InvoiceDetailEntity.builder()
                .invoice(invoice)
                .product(product)
                .price(product.getDiscount() != null
                        ? product.getSalePrice() - (product.getSalePrice() * product.getDiscount().getDiscountPercentage())
                        : product.getSalePrice())
                .quantity(model.getQuantity())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
