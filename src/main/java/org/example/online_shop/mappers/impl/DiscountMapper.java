package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.DiscountDto;
import org.example.online_shop.entities.DiscountEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.DiscountModel;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper implements IBaseMapper<DiscountDto, DiscountModel, DiscountEntity> {
    @Override
    public DiscountDto toDTO(DiscountEntity entity) {
        return DiscountDto.builder()
                .discountId(entity.getDiscountId())
                .name(entity.getName())
                .discountPercentage(entity.getDiscountPercentage())
                .expiryDate(entity.getExpiryDate())
                .build();
    }

    @Override
    public DiscountEntity toEntity(DiscountModel model) {
        return DiscountEntity.builder()
                .discountId(model.getDiscountId())
                .name(model.getName())
                .discountPercentage(model.getDiscountPercentage())
                .expiryDate(model.getExpiryDate())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
