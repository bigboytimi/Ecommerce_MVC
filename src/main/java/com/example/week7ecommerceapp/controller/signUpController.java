package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class signUpController {
    private final UserService userService;

    @GetMapping("/signup")
    public String viewSignUp(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("user-exist", "user-signup");
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(User user, Model model){
        User user1 = userService.findByEmail(user.getEmail());
        if(user1 != null){
            model.addAttribute("user-exist", "user already exists");
            return "signin";
        } else{
            model.addAttribute("user", user);
            userService.addUser(user);
            return "signup";
        }
    }

}
