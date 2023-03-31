package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.service.AdminService;
import com.example.week7ecommerceapp.service.ProductService;
import com.example.week7ecommerceapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final AdminService adminService;
    private final ProductService productService;


    @GetMapping("/")
    public String viewSignInPage(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("invalid email", null);
        return "signin";
    }

    @PostMapping("/signin")
    public String login(@ModelAttribute("user") UserDTO userDTO, Model model, HttpServletRequest httpServletRequest){
        User user = userService.findByEmail(userDTO.getEmail());
        if (user == null){
            model.addAttribute("invalid email", "Email does not exist");
            return "signup";
        }
        HttpSession session = httpServletRequest.getSession(true);
        Long id = user.getId();
        session.setAttribute("usersession", id);
        return "index";
    }

    @GetMapping("/logout-button")
    public ModelAndView logoutUser(ModelAndView mav, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        mav.setViewName("signin");
        return mav;
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
            return "signup-admin";
        }
        HttpSession session = httpServletRequest.getSession();
        Long id = admin.getId();
        session.setAttribute("admin-session", id);
        return "dashboard";
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashboard(ModelAndView model){
        model.addObject("product", new ProductDTO());
        model.setViewName("dashboard");
        return model;
    }

    @PostMapping("/dashboard")
    public ModelAndView addProduct(@ModelAttribute("product") ProductDTO productDTO, ModelAndView mav, RedirectAttributes redirectAttributes){
        productService.saveProduct(productDTO);
        redirectAttributes.addFlashAttribute("product added", "product saved successfully");
        mav.addObject("product added", "product successfully added");
        mav.setViewName("dashboard");
        return mav;
    }
}
