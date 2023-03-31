package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.repository.AdminRepository;
import com.example.week7ecommerceapp.service.AdminService;
import com.example.week7ecommerceapp.service.implementation.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminSignupController {
    private final AdminService adminService;

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
}
