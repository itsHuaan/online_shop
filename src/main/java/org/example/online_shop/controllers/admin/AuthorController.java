package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.models.AuthorModel;
import org.example.online_shop.services.IAuthorService;
import org.example.online_shop.utils.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "05. Author")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/author")
public class AuthorController {
    private final IAuthorService iAuthorService;

    public AuthorController(IAuthorService iAuthorService) {
        this.iAuthorService = iAuthorService;
    }

    @Operation(summary = "Create New Author", tags = {"05. Author"})
    @PostMapping("/create-author")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorModel authorModel) throws IOException {
        int result = iAuthorService.save(authorModel);
        if (result == 1){
            return new ResponseEntity<>("Thêm mới thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Thêm mới thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update Author", tags = {"05. Author"})
    @PostMapping("/update-author/{id}")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorModel authorModel, @PathVariable Long id) throws IOException {
        authorModel.setAuthorId(id);
        int result = iAuthorService.save(authorModel);
        if (result == 2){
            return new ResponseEntity<>("Sửa thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Sửa thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Delete Author", tags = {"05. Author"})
    @GetMapping("/delete-author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id){
        int result = iAuthorService.delete(id);
        if (result == 1){
            return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Xóa thành công", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
