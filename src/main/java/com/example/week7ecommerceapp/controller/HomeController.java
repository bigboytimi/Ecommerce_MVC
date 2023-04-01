package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.service.ProductService;
import com.example.week7ecommerceapp.service.UserService;
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

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public ModelAndView home(ModelAndView mav, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("usersession")==null){
            mav.setViewName("signin");
            return mav;
        }
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("/cart")
    public ModelAndView cartPage(ModelAndView mav, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("usersession")==null){
            mav.setViewName("login");
            return mav;
        }
        mav.setViewName("cart");
        return mav;
    }

    @GetMapping("/wishlist")
    public ModelAndView wishlistPage(ModelAndView mav, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("usersession")==null){
            mav.setViewName("login");
            return mav;
        }
        mav.setViewName("wishlist");
        return mav;
    }

    @GetMapping("/logout")
    public ModelAndView logoutPage(ModelAndView mav, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("usersession")==null){
            mav.setViewName("login");
            return mav;
        }

        mav.setViewName("logout");
        return mav;
    }

//    @PostMapping("/index")
//    public String indexPage(@ModelAttribute("user") UserDTO userDTO, HttpServletRequest httpServletRequest, Model model){
//        User user = userService.findByEmail(userDTO.getEmail());
//        if (user == null){
//            return "signin";
//        }
//        HttpSession session = httpServletRequest.getSession(true);
//        String email = user.getEmail();
//        session.setAttribute("useremail", email);
//        model.addAttribute("listProducts", productService.getAllProduct());
//        return ""
//    }



}


