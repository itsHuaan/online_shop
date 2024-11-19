package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.DiscountDto;
import org.example.online_shop.dto.UserDto;
import org.example.online_shop.models.DiscountModel;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.services.impl.DiscountService;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "04. Discount")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/discount")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Operation(summary = "Get Discount", tags = {"04. Discount"})
    @GetMapping("/get-discount")
    public ResponseEntity<?> getDiscounts() {
        List<DiscountDto> result = discountService.findAll();
        return result != null && !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create New Discount", tags = {"04. Discount"})
    @PostMapping("create-discount")
    public ResponseEntity<?> createDiscount(@RequestBody DiscountModel discount) {
        int result = discountService.save(discount);
        if (result == 1) {
            return new ResponseEntity<>("Discount created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create discount", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Delete Discount", tags = {"04. Discount"})
    @DeleteMapping("/delete-discount")
    public ResponseEntity<?> deleteDiscount(@RequestParam Long discountId) {
        return discountService.delete(discountId) != 0
                ? new ResponseEntity<>("Discount deleted", HttpStatus.OK)
                : new ResponseEntity<>("Failed to delete discount", HttpStatus.NO_CONTENT);
    }
}
