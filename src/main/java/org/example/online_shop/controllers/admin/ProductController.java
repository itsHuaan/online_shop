package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.models.ProductModel;
import org.example.online_shop.services.impl.ProductService;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "06. Product")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get Products", tags = {"06. Product"})
    @GetMapping("/get-product")
    public ResponseEntity<?> getProduct(@RequestParam(required = false) Long productId) {
        if (productId != null) {
            ProductDto result = productService.findById(productId);
            return result != null
                    ? new ResponseEntity<>(result, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ProductDto> result = productService.findAll();
            return result != null && !result.isEmpty()
                    ? new ResponseEntity<>(result, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @Operation(summary = "Create Products", tags = {"06. Product"})
    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductModel product) {
        boolean isExisting = productService.findByNameAndPublishDate(product.getName(), product.getPublishDate()) != null;
        if (isExisting) {
            return new ResponseEntity<>("This book is existing", HttpStatus.CONFLICT);
        }
        int result = productService.save(product);
        return result == 1
                ? new ResponseEntity<>("Product created", HttpStatus.CREATED)
                : new ResponseEntity<>("Failed to create product", HttpStatus.BAD_REQUEST);
    }
}
