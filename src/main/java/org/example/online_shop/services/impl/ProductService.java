package org.example.online_shop.services.impl;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.entities.ProductEntity;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.impl.ProductMapper;
import org.example.online_shop.models.ProductModel;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.repositories.IProductRepository;
import org.example.online_shop.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDTO).toList();
    }

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDTO(Objects.requireNonNull(productRepository.findById(id).orElse(null)));
    }

    @Override
    public int save(ProductModel productModel) {
        ProductEntity currentProduct = productModel.getProductId() != null
                ? productRepository.findById(productModel.getProductId()).orElse(null)
                : null;
        if (currentProduct != null) {
            productRepository.save(mapNonNullFieldsToEntity(productModel, currentProduct));
            return 2;
        } else {
            productRepository.save(productMapper.toEntity(productModel));
            return 1;
        }
    }

    @Override
    public int delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    private ProductEntity mapNonNullFieldsToEntity(ProductModel productModel, ProductEntity productEntity) {
        for (Field userField : productModel.getClass().getDeclaredFields()) {
            try {
                Field dtoField = productEntity.getClass().getDeclaredField(userField.getName());
                userField.setAccessible(true);
                Object value = userField.get(productModel);

                if (value != null) {
                    dtoField.setAccessible(true);
                    dtoField.set(productEntity, value);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field missing in ProfileDto: " + userField.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return productEntity;
    }

    @Override
    public ProductDto findByNameAndPublishDate(String name, Integer publishDate) {
        return productRepository.findByNameAndPublishDate(name, publishDate).map(productMapper::toDTO).orElse(null);
    }
}
