package org.example.online_shop.services;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.models.ProductModel;

public interface IProductService extends IBaseService<ProductDto, ProductModel, Long> {
    ProductDto findByNameAndPublishDate(String name, Integer publishDate);
}
