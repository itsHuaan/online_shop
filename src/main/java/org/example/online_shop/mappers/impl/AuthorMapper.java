package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.AuthorDto;
import org.example.online_shop.entities.AuthorEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.AuthorModel;
import org.springframework.stereotype.Component;


@Component
public class AuthorMapper implements IBaseMapper<AuthorDto, AuthorModel, AuthorEntity> {
    @Override
    public AuthorDto toDTO(AuthorEntity entity) {
        return AuthorDto.builder()
                .authorId(entity.getAuthorId())
                .name(entity.getName())
                .status(entity.getStatus())
                .createdDate(entity.getCreatedDate())
                .build();
    }

    @Override
    public AuthorEntity toEntity(AuthorModel model) {

        return AuthorEntity.builder()
                .authorId(model.getAuthorId())
                .name(model.getName())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
