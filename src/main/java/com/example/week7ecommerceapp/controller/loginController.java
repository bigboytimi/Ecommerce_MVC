package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.DTO.UserDTO;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.repository.UserRepository;
import com.example.week7ecommerceapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class loginController {

    private final UserService userService;

    @GetMapping("/")
    public String viewSignInPage(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("invalid email", null);
        return "signin";
    }

    @PostMapping("/")
    public String login(UserDTO user, Model model, HttpSession httpSession){
        User user1 = userService.findByEmail(user.getEmail());
        if (user1 == null){
            model.addAttribute("invalid email", "Email does not exist");
            return "signin";
        }
        httpSession.setAttribute("user", user);
        return "redirect:/index";
    }
}
