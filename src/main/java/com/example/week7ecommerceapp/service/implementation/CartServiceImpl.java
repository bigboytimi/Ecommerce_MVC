package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.repository.CartRepository;
import com.example.week7ecommerceapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;

    @Override
    public void save(ProductDTO productDTO) {
        Cart cart = new Cart(productDTO);
        cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getProductById(long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()){
            return null;
        } else{
            return cart;
        }
    }

    @Override
    public void deleteProductById(long id) {
        cartRepository.deleteById(id);
    }
}
