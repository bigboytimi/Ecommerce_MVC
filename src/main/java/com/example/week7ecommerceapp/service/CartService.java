package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.model.Cart;

import java.util.List;

public interface CartService {

    public Cart getProductById(Long id);
    public void deleteProductById(Long id);
    void deleteCart();
    List<Cart> getAllProduct();
    Cart addToCart(Cart cart);
    void removeFromCartById(Long id);
    List<Cart> getCartItemsByUserId(Long user_id);
    Cart getCartItemByProductId(Long product_id);

}
