package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.CategoryDto;
import org.example.online_shop.entities.CategoryEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements IBaseMapper <CategoryDto, CategoryModel, CategoryEntity> {

    @Override
    public CategoryDto toDTO(CategoryEntity entity) {
        return CategoryDto.builder()
                .categoryId(entity.getCategoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createDate(entity.getCreatedDate())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public CategoryEntity toEntity(CategoryModel model) {
        return CategoryEntity.builder()
                .categoryId(model.getCategoryId())
                .name(model.getName())
                .description(model.getDescription())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
