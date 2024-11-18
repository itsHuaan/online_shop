package org.example.online_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("currentPath", "/index");
        return "index";
    }
}
