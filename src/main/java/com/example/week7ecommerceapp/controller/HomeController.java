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
        if(session.getAttribute("usernumber")==null){
            mav.setViewName("signin");
            return mav;
        }
        mav.setViewName("index");
        return mav;
    }




}
