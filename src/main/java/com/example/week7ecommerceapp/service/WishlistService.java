package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlist();
    void addWishlist(Wishlist wishlist);
    void removeWishList(Long id);
}
