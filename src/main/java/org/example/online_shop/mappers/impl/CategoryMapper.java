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
                .description(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    @Override
    public CategoryEntity toEntity(CategoryModel model) {
        return CategoryEntity.builder()
                .name(model.getName())
                .description(model.getDescription())
                .status(model.getStatus())
                .createdDate(model.getCreatedDate())
                .build();
    }
}
