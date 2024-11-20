package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.models.CategoryModel;
import org.example.online_shop.services.ICategoryService;
import org.example.online_shop.utils.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "03. Category")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/category")
public class CategoryController {

    private final ICategoryService iCategoryService;

    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }


    @Operation(summary = "Create New Category", tags = {"03. Category"})
    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel categoryModel){
        int check = iCategoryService.checkCategory(categoryModel);
        if (check == 0){
            return new ResponseEntity<>("Danh mục đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        int result = iCategoryService.save(categoryModel);
        if (result == 1){
            return new ResponseEntity<>("Thêm mới thành công", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Thêm mới thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update Category", tags = {"03. Category"})
    @PostMapping("/update-category")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryModel categoryModel, @RequestParam("id") Long id){
        categoryModel.setCategoryId(id);
        int result = iCategoryService.save(categoryModel);
        if (result == 2){
            return new  ResponseEntity<>("Sửa thành công", HttpStatus.OK);
        } else {
            return  new ResponseEntity<>("Sửa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete Category", tags = {"03. Category"})
    @GetMapping("/delete-category")
    public ResponseEntity<?> deleteCategory(@RequestParam("id") long id){
        int result = iCategoryService.delete(id);
        if (result == 1){
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Xóa thành công", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
