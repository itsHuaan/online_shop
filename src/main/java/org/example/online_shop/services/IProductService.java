package org.example.online_shop.services;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.models.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IProductService extends IBaseService<ProductDto, ProductModel, Long> {
    ProductDto findByNameAndPublishDate(String name, Integer publishDate);

    int updateStockAndSold(Long id, int sellQuantity);
    Page<ProductDto> filterProduct(String name, Long authorId, Long categoryId, Pageable pageable);
}
