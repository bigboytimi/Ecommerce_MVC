package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/")
    public String viewSignInPage(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("invalid email", null);
        return "signin";
    }

    @PostMapping("/")
    public String login(UserDTO userDTO, Model model, HttpServletRequest httpServletRequest){
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

}
