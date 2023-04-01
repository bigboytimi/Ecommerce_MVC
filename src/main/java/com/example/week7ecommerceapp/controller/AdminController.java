package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.service.AdminService;
import com.example.week7ecommerceapp.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final AdminService adminService;

    @GetMapping("/dashboard")
    public String getDashboard(HttpServletRequest httpServletRequest, Model model){
        HttpSession session = httpServletRequest.getSession();
        List<Product> products = productService.getAllProduct();
        String email = (String) session.getAttribute("email");
//        Long id = (Long) session.getAttribute("id");
//        Admin admin = adminService.findByEmail(email);
        model.addAttribute("admin", new Admin());
        model.addAttribute("products", products);
//        model.addAttribute("id", admin.getId());
        model.addAttribute("product", new ProductDTO());

        if(session.getAttribute(email)==null){
            return "loginAdmin";
        } else{
            return "add-product";
        }
    }

    @GetMapping("/add-product")
    public String addProduct(Model model){
        model.addAttribute("product", new ProductDTO());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("product-added", "product added successfully");
        productService.saveProduct(productDTO);
        return "dashboard";
    }


}
