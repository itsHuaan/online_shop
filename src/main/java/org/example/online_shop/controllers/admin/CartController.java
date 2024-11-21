package org.example.online_shop.controllers.admin;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.CartDetailDto;
import org.example.online_shop.dto.CartDto;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.services.impl.CartDetailService;
import org.example.online_shop.services.impl.CartService;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "06. Cart")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/cart")
public class CartController {
    private final CartService cartService;
    private final CartDetailService cartDetailService;

    @Autowired
    public CartController(CartService cartService, CartDetailService cartDetailService) {
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
    }

    @Operation(summary = "Add book to cart", tags = {"06. Cart"})
    @PostMapping("add-to-cart/{userId}")
    public ResponseEntity<?> addToCart(@RequestParam int quantity,
                                       @RequestParam Long productId,
                                       @PathVariable Long userId) {
        CartDto cart = cartService.getCartDto(userId);
        CartDetailDto cartDetails = cartDetailService.getCartDetail(cart.getCartId(), productId);
        cartDetailService.updateQuantity(cartDetails.getCartId(), quantity);
        return new ResponseEntity<>("Product added to cart", HttpStatus.CREATED);
    }
}
