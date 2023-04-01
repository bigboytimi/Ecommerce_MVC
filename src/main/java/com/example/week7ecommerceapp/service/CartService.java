package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Cart;

import java.util.Optional;

public interface CartService {
    void save(ProductDTO productDTO);
    public Optional<Cart> getProductById(long id);
    public void deleteProductById(long id);

}
