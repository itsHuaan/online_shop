package org.example.online_shop.services.impl;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.entities.ProductEntity;
import org.example.online_shop.entities.UserEntity;
import org.example.online_shop.mappers.impl.ProductMapper;
import org.example.online_shop.models.ProductModel;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.repositories.IProductRepository;
import org.example.online_shop.services.IProductService;
import org.example.online_shop.utils.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
        return productRepository.findAll(Specification.where(ProductSpecifications.isActive())).stream().map(productMapper::toDTO).toList();
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
        ProductEntity currentProduct = productRepository.findById(id).orElse(null);
        if (currentProduct != null) {
            currentProduct.setStatus(false);
            productRepository.save(currentProduct);
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

    @Override
    public int updateStockAndSold(Long id, int sellQuantity) {
        ProductEntity currentProduct = productRepository.findById(id).orElse(null);
        if (currentProduct != null) {
            int stock = currentProduct.getStock();
            int sold = currentProduct.getSold();
            if (stock < 1){
                return 0;
            } else if (stock < sellQuantity){
                sold += stock;
                stock = 0;
                currentProduct.setStock(stock);
                currentProduct.setSold(sold);
                productRepository.save(currentProduct);
                return 1;
            }
            sold += sellQuantity;
            stock -= sellQuantity;
            currentProduct.setStock(stock);
            currentProduct.setSold(sold);
            productRepository.save(currentProduct);
            return 1;
        }
        return 0;
    }

    @Override
    public List<ProductDto> filterProduct(String name, Long authorId, Long categoryId) {
        Specification<ProductEntity> specification = Specification.where(ProductSpecifications.isActive());
        if (name != null){
            specification = specification.and(ProductSpecifications.likeName(name));
        }
        if (authorId != null){
            specification = specification.and(ProductSpecifications.hasAuthor(authorId));
        }
        if (categoryId != null){
            specification = specification.and(ProductSpecifications.inCategory(categoryId));
        }
        return productRepository.findAll(specification).stream().map(productMapper::toDTO).toList();
    }
}
