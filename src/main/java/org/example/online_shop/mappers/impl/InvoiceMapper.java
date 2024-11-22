package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.InvoiceDto;
import org.example.online_shop.entities.InvoiceEntity;
import org.example.online_shop.entities.PaymentMethodEntity;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.InvoiceModel;

public class InvoiceMapper implements IBaseMapper<InvoiceDto, InvoiceModel, InvoiceEntity> {
    @Override
    public InvoiceDto toDTO(InvoiceEntity entity) {
        return InvoiceDto.builder()
                .invoiceId(entity.getInvoiceId())
                .userId(entity.getUser().getUserId())
                .deliveryAddress(entity.getDeliveryAddress())
                .totalAmount(entity.getTotalAmount())
                .note(entity.getNote())
                .paymentMethod(entity.getPaymentMethod().getType())
                .deliveryStatus(entity.getDeliveryStatus())
                .status(entity.getStatus())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    @Override
    public InvoiceEntity toEntity(InvoiceModel model) {
        PaymentMethodEntity paymentMethod = new PaymentMethodEntity();
        paymentMethod.setPaymentMethodId(model.getPaymentMethodId());
        UserEntity user = new UserEntity();
        user.setUserId(model.getUserId());
        return InvoiceEntity.builder()
                .invoiceId(model.getInvoiceId())
                .user(user)
                .deliveryAddress(model.getDeliveryAddress())
                .totalAmount(model.getTotalAmount())
                .note(model.getNote())
                .paymentMethod(paymentMethod)
                .deliveryStatus(model.getDeliveryStatus())
                .status(model.isStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
