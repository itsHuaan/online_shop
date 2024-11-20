package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.models.ProductModel;
import org.example.online_shop.services.IProductService;
import org.example.online_shop.services.impl.ProductService;
import org.example.online_shop.services.impl.UserService;
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
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get Products", tags = {"06. Product"})
    @GetMapping("/get-product")
    public ResponseEntity<?> getProduct(@RequestParam(required = false) Long productId,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) Long authorId,
                                        @RequestParam(required = false) Long categoryId) {
        if (productId != null) {
            ProductDto result = productService.findById(productId);
            return result != null
                    ? new ResponseEntity<>(result, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ProductDto> result = productService.filterProduct(name, authorId, categoryId);
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

    @Operation(summary = "Update Products", tags = {"06. Product"})
    @PutMapping("/update-product")
    public ResponseEntity<?> updateProduct(@RequestParam Long id, @RequestBody ProductModel product) {
        if (productService.findById(id) == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        product.setProductId(id);
        return productService.save(product) == 2
                ? new ResponseEntity<>("Product updated", HttpStatus.OK)
                : new ResponseEntity<>("Failed to update product", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Delete Products", tags = {"06. Product"})
    @DeleteMapping("/delete-product")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id) {
        return productService.delete(id) != 0
                ? new ResponseEntity<>("Product deleted", HttpStatus.OK)
                : new ResponseEntity<>("Failed to delete product", HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "Sell Products", tags = {"06. Product"})
    @GetMapping("sell-product")
    public ResponseEntity<?> sellProduct(@RequestParam Long productId, @RequestParam int quantity) {
        return productService.updateStockAndSold(productId, quantity) != 0
                ? new ResponseEntity<>("Sold " + quantity + " items", HttpStatus.OK)
                : new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
}
