package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> getAllWishlist();
    Wishlist addWishlist(Wishlist wishlist);
    void removeWishList(Long id);
    Wishlist getWishlistByProductId(Long id);
}
