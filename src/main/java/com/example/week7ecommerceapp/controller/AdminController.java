package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.model.Wishlist;
import com.example.week7ecommerceapp.service.AdminService;
import com.example.week7ecommerceapp.service.CartService;
import com.example.week7ecommerceapp.service.ProductService;
import com.example.week7ecommerceapp.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminController {
    private final ProductService productService;
    private final AdminService adminService;
    private final CartService cartService;
    private final WishlistService wishlistService;

    public AdminController(ProductService productService, AdminService adminService, CartService cartService, WishlistService wishlistService) {
        this.productService = productService;
        this.adminService = adminService;
        this.cartService = cartService;
        this.wishlistService = wishlistService;
    }


    @GetMapping( value="/dashboard")
    public String dashboard(@ModelAttribute("product") ProductDTO productDTO, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        String email = (String) session.getAttribute("email");
        Admin admin = adminService.findByEmail(email);
        if(admin == null){
            return "loginAdmin";
        }else {
            return "/dashboard";
        }
    }
    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO){
        return "dashboard";
    }
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO, RedirectAttributes redirectAttributes){
        productService.saveProduct(productDTO);
        redirectAttributes.addFlashAttribute("product-added", "product added successfully");
        return "product-add";
    }

    @GetMapping("/b2dashboard")
    public String backToDashboard(){
        return "redirect:/dashboard";
    }

    @GetMapping("/logoutAdmin")
    public String logoutAdmin(){
        return "redirect:/loginAdmin";
    }

    @GetMapping("/signupAdmin")
    public String getAdminRegister(Model model){
        model.addAttribute("admin", new AdminDTO());
        model.addAttribute("admin-exist", "admin-signup");
        return "signupAdmin";
    }

    @PostMapping("/signupAdmin")
    public String registerAdmin(@ModelAttribute("admin") AdminDTO adminDTO, Model model){
        adminService.save(adminDTO);
        return "loginAdmin";
    }

    @GetMapping("/loginAdmin")
    public String adminLogin(Model model, HttpServletRequest httpServletRequest){
        model.addAttribute("admin", new AdminDTO());
        model.addAttribute("invalid email", null);
        return "loginAdmin";
    }

    @PostMapping("/loginAdmin")
    public String registerAdmin(@ModelAttribute("admin") AdminDTO adminDTO, Model model, HttpServletRequest httpServletRequest){
        Admin admin = adminService.findByEmail(adminDTO.getEmail());
        if(admin == null){
            model.addAttribute("invalid email", "email not found");
            return "signupAdmin";
        }
        HttpSession session = httpServletRequest.getSession();
        String email = admin.getEmail();
        session.setAttribute("email", email);
        model.addAttribute("product", new ProductDTO());
        return "redirect:/dashboard";
    }

    @GetMapping("/viewProducts")
    public String viewAdminProducts(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("productList", products);
        return "viewProducts";
    }


    @PostMapping("/addToCart")
    public String addCart(@RequestParam("product_id") Long product_id, @RequestParam("product_price") Double price, HttpSession session){
        Long userId = (Long) session.getAttribute("usersession");
        if(userId == null){
            return "redirect:/signin";
        }
        Cart cart = new Cart();
        cart.setUser_id(userId);
        cart.setProduct_id(product_id);
        cart.setPrice(price);
        cartService.addToCart(cart);
        return "redirect:/index";
    }

    @PostMapping("/addToWishlist")
    public String addWishlist(@RequestParam("product_id") Long id,
                              @RequestParam("productName") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") String price,
                              HttpSession session){
        Long userId = (Long) session.getAttribute("usersession");
        if(userId == null){
            return "redirect:/signin";
        }
        Wishlist wishlist = new Wishlist();
        wishlist.setProduct_id(id);
        wishlist.setProductName(name);
        wishlist.setDescription(description);
        wishlist.setPrice(price);
        wishlistService.addWishlist(wishlist);
        return "redirect:/index";
    }

}
