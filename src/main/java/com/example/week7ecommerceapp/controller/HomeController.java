package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private ProductService productService;

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



}
