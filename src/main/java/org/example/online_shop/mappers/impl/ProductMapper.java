package org.example.online_shop.mappers.impl;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.entities.AuthorEntity;
import org.example.online_shop.entities.CategoryEntity;
import org.example.online_shop.entities.DiscountEntity;
import org.example.online_shop.entities.ProductEntity;
import org.example.online_shop.mappers.IBaseMapper;
import org.example.online_shop.models.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements IBaseMapper<ProductDto, ProductModel, ProductEntity> {
    @Override
    public ProductDto toDTO(ProductEntity entity) {
        return ProductDto.builder()
                .productId(entity.getProductId())
                .name(entity.getName())
                .description(entity.getDescription())
                .salePrice(entity.getSalePrice())
                .totalPage(entity.getTotalPage())
                .stock(entity.getStock())
                .sold(entity.getSold())
                .totalPage(entity.getTotalPage())
                .categoryName(entity.getCategory().getName())
                .discountPercentage(entity.getDiscount().getDiscountPercentage())
                .authorName(entity.getAuthor().getName())
                .publishDate(entity.getPublishDate())
                .importPrice(entity.getImportPrice())
                .status(entity.getStatus())
                .build();
    }

    @Override
    public ProductEntity toEntity(ProductModel model) {
        AuthorEntity author = new AuthorEntity();
        author.setAuthorId(model.getAuthorId());
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(model.getCategoryId());
        DiscountEntity discount = new DiscountEntity();
        discount.setDiscountId(model.getDiscountId());
        return ProductEntity.builder()
                .productId(model.getProductId())
                .name(model.getName())
                .description(model.getDescription())
                .salePrice(model.getSalePrice())
                .totalPage(model.getTotalPage())
                .stock(model.getStock())
                .sold(model.getSold())
                .category(category)
                .author(author)
                .discount(discount)
                .importPrice(model.getImportPrice())
                .publishDate(model.getPublishDate())
                .createdDate(model.getCreatedDate())
                .status(model.getStatus())
                .build();
    }
}
