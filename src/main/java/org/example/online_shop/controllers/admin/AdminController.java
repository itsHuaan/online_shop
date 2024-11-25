package org.example.online_shop.controllers.admin;

import org.example.online_shop.dto.ProductDto;
import org.example.online_shop.dto.UserDto;
import org.example.online_shop.models.*;
import org.example.online_shop.services.*;
import org.example.online_shop.services.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final IProductService productService;
    private final IUserService userService;
    private final ICategoryService categoryService;
    private final IAuthorService authorService;
    private final IDiscountService discountService;
    private final IPostService postService;

    @Autowired
    public AdminController(IProductService productService, IUserService userService, ICategoryService categoryService, IAuthorService authorService, IDiscountService discountService, IPostService postService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.discountService = discountService;
        this.postService = postService;
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
    public String createProduct(Model model, @ModelAttribute("ProductModel") ProductModel productModel, RedirectAttributes attributes) {
        int result = productService.save(productModel);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Thêm  thành công");
        } else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/create-post")
    public String createPost(Model model, @ModelAttribute("postModel") PostModel postModel, RedirectAttributes attributes) {
        int result = postService.save(postModel);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/posts";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(Model model, RedirectAttributes attributes, @PathVariable Long id) {
        int userRole = userService.findById(id).getRoleId();
        int result = userService.delete(id);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Xóa thành công");
        } else {
            attributes.addFlashAttribute("error", "Xóa thất bại");
        }
        return userRole == 1
                ? "redirect:/admin/admins"
                : "redirect:/admin/customers";
    }

    @GetMapping("/delete-post/{id}")
    public String deletePost(Model model, RedirectAttributes attributes, @PathVariable Long id) {
        int result = postService.delete(id);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Xóa thành công");
        } else {
            attributes.addFlashAttribute("error", "Xóa thất bại");
        }
        return "redirect:/admin/posts";
    }

    @PostMapping("/update-product/{id}")
    public String updateProduct(Model model, @ModelAttribute("ProductModel") ProductModel productModel, RedirectAttributes attributes, @PathVariable Long id) {
        productModel.setProductId(id);
        int result = productService.save(productModel);
        if (result == 2) {
            attributes.addFlashAttribute("success", "Cập nhật thành công");
        } else {
            attributes.addFlashAttribute("error", "Cập nhật thất bại");
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/delete-product/{id}")
    public String deleteProduct(Model model, RedirectAttributes attributes, @PathVariable Long id) {
        int result = productService.delete(id);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Xóa thành công");
        } else {
            attributes.addFlashAttribute("error", "Xóa thất bại");
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/admins")
    public String listAdmins(Model model) {
        model.addAttribute("admins", userService.findByRoleId(1L));
        model.addAttribute("currentPath", "/admins");
        return "admin/user/admins";
    }

    @GetMapping("/customers")
    public String listCustomers(Model model) {
        model.addAttribute("customers", userService.findByRoleId(2L));
        model.addAttribute("currentPath", "/customers");
        return "admin/user/customers";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("userModel", new UserModel());
        model.addAttribute("currentPath", "/add-user");
        return "admin/user/add-user";
    }

    @PostMapping("/create-user")
    public String addUser(Model model, @ModelAttribute("userModel") UserModel userModel, RedirectAttributes attributes) {
        userModel.setRoleId(1);
        boolean isExisting = userService.findByEmailOrUsername(userModel.getEmail(), userModel.getUsername()) != null;
        if (isExisting) {
            attributes.addFlashAttribute("error", "Người dùng đã tồn tại");
        }
        int result = userService.save(userModel);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/admins";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authorModel", new AuthorModel());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("currentPath", "/authors");
        return "admin/author/authors";
    }

    @PostMapping("/add-author")
    public String addAuthor(Model model, @ModelAttribute("authorModel") AuthorModel authorModel, RedirectAttributes attributes) {
        int result = authorService.save(authorModel);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/authors";
    }

    @PostMapping("/add-category")
    public String addCategory(Model model, @ModelAttribute("categoryModel") CategoryModel categoryModel, RedirectAttributes attributes) {
        int result = categoryService.save(categoryModel);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/add-discount")
    public String addDiscount(Model model, @ModelAttribute("discountModel") DiscountModel discountModel, RedirectAttributes attributes) {
        int result = discountService.save(discountModel);
        if (result == 1) {
            attributes.addFlashAttribute("success", "Thêm thành công");
        } else {
            attributes.addFlashAttribute("error", "Thêm thất bại");
        }
        return "redirect:/admin/discounts";
    }

    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categoryModel", new CategoryModel());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentPath", "/categories");
        return "admin/category/categories";
    }

    @GetMapping("/discounts")
    public String listPromotions(Model model) {
        model.addAttribute("discountModel", new DiscountModel());
        model.addAttribute("discounts", discountService.findAll());
        model.addAttribute("currentPath", "/discounts");
        return "admin/discount/discounts";
    }


    @GetMapping("/posts")
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("currentPath", "/posts");
        return "admin/post/posts";
    }

    @GetMapping("/add-posts")
    public String addPost(Model model) {
        model.addAttribute("postModel", new PostModel());
        model.addAttribute("currentPath", "/add-posts");
        return "admin/post/add-post";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(Model model, @PathVariable Long id){
        ProductDto productDto = productService.findById(id);
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("discounts", discountService.findAll());
        return "admin/product/edit-product";
    }

    @GetMapping("/invoices")
    public String listInvoice(Model model)
    {
        model.addAttribute("currentPath", "/invoices");
        return "admin/invoice/invoices";
    }
}
