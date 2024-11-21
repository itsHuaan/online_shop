package org.example.online_shop.controllers.admin;

import org.example.online_shop.models.ProductModel;
import org.example.online_shop.services.IAuthorService;
import org.example.online_shop.services.ICategoryService;
import org.example.online_shop.services.IDiscountService;
import org.example.online_shop.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final ICategoryService categoryService;
    private final IAuthorService authorService;
    private final IDiscountService discountService;
    @Autowired
    public AdminController(ProductService productService, ICategoryService categoryService, IAuthorService authorService, IDiscountService discountService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.discountService = discountService;
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
        ProductModel productModel = new ProductModel();
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("discounts", discountService.findAll());
        model.addAttribute("ProductModel", productModel);
        model.addAttribute("currentPath", "/add-products");
        return "admin/product/add-product";
    }

    @PostMapping("/create-product")
    public String createProduct(Model model, @ModelAttribute("ProductModel") ProductModel productModel, RedirectAttributes attributes) throws IOException {
        int ressult = productService.save(productModel);
        if (ressult == 1) {
            attributes.addFlashAttribute("success", "Thêm  thành công");
        }else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(Model model, @ModelAttribute("ProductModel") ProductModel productModel, RedirectAttributes attributes, @PathVariable Long id) throws IOException {
        productModel.setProductId(id);
        int ressult = productService.save(productModel);
        if (ressult == 2) {
            attributes.addFlashAttribute("success", "Cập nhật  thành công");
        }else {
            attributes.addFlashAttribute("error", "Cập nhật thất bại");
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/delete-product/{id}")
    public String updateProduct(Model model, RedirectAttributes attributes, @PathVariable Long id) throws IOException {
        int ressult = productService.delete(id);
        if (ressult == 1) {
            attributes.addFlashAttribute("success", "Xóa  thành công");
        }else {
            attributes.addFlashAttribute("error", "Xóa thất bại");
        }
        return "redirect:/admin/products";
    }
}
