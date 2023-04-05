package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.repository.CartRepository;
import com.example.week7ecommerceapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository cartRepository;



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

    @Override
    public void deleteCart() {
        cartRepository.deleteAll();
    }

    @Override
    public List<Cart> getAllProduct() {
        return cartRepository.findAll();
    }

    @Override
    public void addToCart(Cart item) {
        Cart existingItem = cartRepository.findByProductIdAndUserId(item.getUser_id(), item.getProduct_id());
        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartRepository.save(existingItem);
        } else{
            item.setQuantity(1);
            cartRepository.save(item);
        }
    }

    @Override
    public void removeFromCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getCartItemsByUserId(Long user_id) {
        return cartRepository.findByUser_id(user_id);
    }
}
