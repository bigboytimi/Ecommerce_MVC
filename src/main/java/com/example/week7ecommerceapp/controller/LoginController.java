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
        model.addAttribute("listProducts", productService.getAllProduct());
        model.addAttribute("usersession", session.getAttribute("usersession"));
        return "index";
    }

    @GetMapping("/index")
    public ModelAndView getIndex(ModelAndView model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("usersession")==null){
            model.setViewName("signin");
            return model;
        }
        model.setViewName("index");
        model.addObject("listProducts", productService.getAllProduct());
        return model;
    }

    @GetMapping("/logout-button")
    public ModelAndView logoutUser(ModelAndView mav, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        mav.setViewName("signin");
        return mav;
    }

    @GetMapping("/signup")
    public String viewSignUp(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("user-exist", "user-signup");
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO, Model model){
        User user1 = userService.findByEmail(userDTO.getEmail());
        if(user1 != null){
            model.addAttribute("user-exist", "user already exists");
            return "signup";
        } else{
            model.addAttribute("user", userDTO);
            userService.addUser(userDTO);
            return "signin";
        }
    }
}
