package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.OtpDto;
import org.example.online_shop.entities.OtpEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.OtpModel;
import org.springframework.stereotype.Component;

@Component
public class OtpMapper implements IBaseMapper<OtpDto, OtpModel, OtpEntity> {
    @Override
    public OtpDto toDTO(OtpEntity entity) {
        return OtpDto.builder()
                .otpCode(entity.getOtpCode())
                .email(entity.getEmail())
                .status(entity.getStatus())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    @Override
    public OtpEntity toEntity(OtpModel model) {
        return OtpEntity.builder()
                .otpCode(model.getOtpCode())
                .email(model.getEmail())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
