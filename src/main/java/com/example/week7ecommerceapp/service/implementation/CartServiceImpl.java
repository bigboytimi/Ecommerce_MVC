package com.example.week7ecommerceapp.service.implementation;

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
    public Cart getProductById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        Cart testCart = null;
        if (cart.isPresent()){
            testCart = cart.get();
        } else{
            throw new RuntimeException("Cart with " + id + " not found");
        }
        return testCart;
    }

    @Override
    public void deleteProductById(Long id) {
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
    public Cart addToCart(Cart item) {
        Cart existingItem = cartRepository.findByProductIdAndUserId(item.getUser_id(), item.getProduct_id());
        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            cartRepository.save(existingItem);
        } else{
            item.setQuantity(1);
            cartRepository.save(item);
        }
        return existingItem;
    }

    @Override
    public void removeFromCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getCartItemsByUserId(Long user_id) {
        return cartRepository.findByUser_id(user_id);
    }

    @Override
    public Cart getCartItemByProductId(Long product_id) {
        return cartRepository.findByProductId(product_id);
    }
}
