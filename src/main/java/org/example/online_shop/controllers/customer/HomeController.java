package org.example.online_shop.controllers.customer;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.services.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final IProductService productService;

    public HomeController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        return "customer/about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "customer/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "customer/register";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("products", productDtos);
        return "customer/product";
    }
}