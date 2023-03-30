package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

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
