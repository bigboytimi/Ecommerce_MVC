package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.model.Wishlist;
import com.example.week7ecommerceapp.repository.WishlistRepository;
import com.example.week7ecommerceapp.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private final WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> getAllWishlist() {
        return wishlistRepository.findAll();
    }

    @Override
    public void addWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    @Override
    public void removeWishList(Long id) {
        wishlistRepository.deleteById(id);
    }
}
