package org.example.online_shop.controllers.customer;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.services.IProductService;
import org.example.online_shop.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {
    private final IProductService productService;
    private final IUserService userService;
    public HomeController(IProductService productService, IUserService userService) {
        this.productService = productService;
        this.userService = userService;
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
        UserModel userModel = new UserModel();
        model.addAttribute("UserModel", userModel);
        return "customer/register";
    }

    @GetMapping("/signup")
    public String signup(@ModelAttribute("UserModel") UserModel userModel, RedirectAttributes attributes) {
        int result = userService.save(userModel);
        if (result == 1){
            attributes.addFlashAttribute("success", "Đăng ký thành công");
        }else {
            attributes.addFlashAttribute("error", "Đăng ký thất bại");
        }
        return "redirect:/login";
    }

    @GetMapping("/product")
    public String product(Model model) {
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("products", productDtos);
        return "customer/product";
    }

}