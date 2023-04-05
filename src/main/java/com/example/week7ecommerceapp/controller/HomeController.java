package com.example.week7ecommerceapp.controller;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.model.Wishlist;
import com.example.week7ecommerceapp.service.CartService;
import com.example.week7ecommerceapp.service.ProductService;
import com.example.week7ecommerceapp.service.UserService;
import com.example.week7ecommerceapp.service.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private WishlistService wishlistService;


    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session){
        Long userId = (Long) session.getAttribute("usersession");
        List<Cart> carts = cartService.getCartItemsByUserId(userId);
        model.addAttribute( "carts", carts);
        return "cart";
    }



    @GetMapping("/wishlist")
    public String wishlistPage(Model model, HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("usersession")==null){
            return "signin";
        }
        List<Wishlist> wishlists = wishlistService.getAllWishlist();
        model.addAttribute("wishlist", wishlists);
        return "wishlist";
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

    @PostMapping("/removeCartItem")
    public String removeItem(@RequestParam("cartId") Long id){
        cartService.removeFromCartById(id);
        return "redirect:/cart";
    }
}


