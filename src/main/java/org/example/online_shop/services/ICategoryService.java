package org.example.online_shop.services;

import org.example.online_shop.dto.CategoryDto;
import org.example.online_shop.models.CategoryModel;

public interface ICategoryService extends IBaseService<CategoryDto, CategoryModel, Long>{
    int checkCategory(CategoryModel categoryModel);
}
