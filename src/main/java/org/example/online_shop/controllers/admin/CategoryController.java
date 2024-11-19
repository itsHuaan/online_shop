package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.models.CategoryModel;
import org.example.online_shop.services.ICategoryService;
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

    private final ICategoryService iCategoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }


    @Operation(summary = "Create New Category", tags = {"03. Category"})
    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel categoryModel){
        int result = iCategoryService.save(categoryModel);
        String noti = "";
        if (result == 1){
             noti = "Thêm mới thành công";
        } else if (result == 2) {
             noti = "Tên danh mục đã tồn tại";
        }else {
             noti = "Có lỗi trong quá trình tạo";
        }
        return ResponseEntity.ok(noti);
    }

    @Operation(summary = "Update Category", tags = {"03. Category"})
    @PostMapping("/update-category")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryModel categoryModel){

        return ResponseEntity.ok(0);
    }

}
