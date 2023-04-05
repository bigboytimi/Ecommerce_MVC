package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {

    public Optional<Cart> getProductById(long id);
    public void deleteProductById(long id);
    void deleteCart();
    List<Cart> getAllProduct();
    void addToCart(Cart cart);
    void removeFromCartById(Long id);
    List<Cart> getCartItemsByUserId(Long user_id);

}
