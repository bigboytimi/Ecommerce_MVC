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
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final ProductService productService;
    @Autowired
    private final AdminService adminService;


    @GetMapping( value="/dashboard")
    public String dashboard(@ModelAttribute("product") ProductDTO productDTO, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        String email = (String) session.getAttribute("email");
        Admin admin = adminService.findByEmail(email);
        if(admin == null){
            return "loginAdmin";
        }else {
            return "dashboard";
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
}
