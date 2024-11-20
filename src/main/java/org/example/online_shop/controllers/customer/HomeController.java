package org.example.online_shop.controllers.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
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
        return "customer/product";
    }
}