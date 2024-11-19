package org.example.online_shop.services.impl;

import org.example.online_shop.dto.CategoryDto;
import org.example.online_shop.entities.CategoryEntity;
import org.example.online_shop.mappers.impl.CategoryMapper;
import org.example.online_shop.models.CategoryModel;
import org.example.online_shop.repositories.ICategoryRepository;
import org.example.online_shop.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(ICategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> findAll() {
        return null;
    }

    @Override
    public CategoryDto findById(Long id) {
        return null;
    }

    @Override
    public CategoryDto save(CategoryModel categoryModel) {
        Optional<CategoryEntity> category = categoryRepository.findByName(categoryModel.getName());
        if (category.isPresent() && !category.isPresent()){
            categoryRepository.save(categoryMapper.toEntity(categoryModel));
        }
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }


}
