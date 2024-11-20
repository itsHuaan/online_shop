package org.example.online_shop.controllers.admin;

import org.example.online_shop.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String welcome(Model model) {
        return "admin/welcome";
    }

    @GetMapping("/homepage")
    public String homepage(Model model) {
        model.addAttribute("currentPath", "/homepage");
        return "admin/homepage";
    }

    @GetMapping("/products")
    public String listProduct(Model model) {
        model.addAttribute("books", productService.findAll());
        model.addAttribute("currentPath", "/products");
        return "admin/product/products";
    }

    @GetMapping("/add-products")
    public String addProduct(Model model) {
        model.addAttribute("currentPath", "/add-products");
        return "admin/product/add-product";
    }
}
