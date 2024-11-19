package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.models.CategoryModel;
import org.example.online_shop.services.impl.CategoryService;
import org.example.online_shop.utils.Const;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "03. Category")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/user")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Create New Category", tags = {"03. Category"})
    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel categoryModel){
        return null;
    }

}
