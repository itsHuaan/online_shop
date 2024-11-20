package org.example.online_shop.services.impl;

import org.example.online_shop.dto.CategoryDto;
import org.example.online_shop.entities.AuthorEntity;
import org.example.online_shop.entities.CategoryEntity;
import org.example.online_shop.mappers.impl.CategoryMapper;
import org.example.online_shop.models.AuthorModel;
import org.example.online_shop.models.CategoryModel;
import org.example.online_shop.repositories.ICategoryRepository;
import org.example.online_shop.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
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
    public int save(CategoryModel categoryModel) {
        try{
            if (categoryModel.getCategoryId() == null){
                CategoryEntity category1 = categoryMapper.toEntity(categoryModel);
                category1.setStatus(true);
                category1.setCreatedDate(LocalDateTime.now());
                categoryRepository.save(category1);
                return 1;
            } else {
                Optional<CategoryEntity> category = categoryRepository.findById(categoryModel.getCategoryId());
                categoryRepository.save(mapNonNullFieldsToEntity(categoryModel, category.get()));
                return 2;
            }
        }catch (Exception e){
            return 0;
        }

    }

    @Override
    public int delete(Long id) {
        try{
            Optional<CategoryEntity> category = categoryRepository.findById(id);
            if (!category.isEmpty()){
                CategoryEntity category1 = category.get();
                category1.setStatus(false);
                categoryRepository.save(category1);
                return 1;
            }else {
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }

    private CategoryEntity mapNonNullFieldsToEntity(CategoryModel categoryModel, CategoryEntity categoryEntity) {
        for (Field userField : categoryModel.getClass().getDeclaredFields()) {
            try {
                Field dtoField = categoryEntity.getClass().getDeclaredField(userField.getName());
                userField.setAccessible(true);
                Object value = userField.get(categoryModel);

                if (value != null) {
                    dtoField.setAccessible(true);
                    dtoField.set(categoryEntity, value);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Field missing in ProfileDto: " + userField.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return categoryEntity;
    }
    @Override
    public int checkCategory(CategoryModel categoryModel){
        Optional<CategoryEntity> category = categoryRepository.findByName(categoryModel.getName());
        if (category.isEmpty()){
            return 1;
        }else {
            return 0;
        }
    }

}
