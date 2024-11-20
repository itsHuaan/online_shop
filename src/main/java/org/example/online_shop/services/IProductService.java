package org.example.online_shop.services;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.models.ProductModel;

import java.util.List;

public interface IProductService extends IBaseService<ProductDto, ProductModel, Long> {
    ProductDto findByNameAndPublishDate(String name, Integer publishDate);

    int updateStockAndSold(Long id, int sellQuantity);
    List<ProductDto> filterProduct(String name, Long authorId, Long categoryId);
}
