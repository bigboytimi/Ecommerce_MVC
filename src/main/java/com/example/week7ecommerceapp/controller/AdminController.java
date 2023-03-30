package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;

    @GetMapping("/add-product")
    public String addProduct(Model model){
        model.addAttribute("product", new ProductDTO());
        return "dashboard";
    }

    @PostMapping("/add-product")
    public String saveProduct(@ModelAttribute("product") ProductDTO productDTO, RedirectAttributes redirectAttributes){
        ModelAndView mav = new ModelAndView();
        Product product = new Product(productDTO);
        productService.saveProduct(product);
        mav.addObject("product-added", "product saved successful");
        redirectAttributes.addFlashAttribute("product-added", "product saved successful");
        return "redirect:dashboard";
    }


}
