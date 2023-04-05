package com.example.week7ecommerceapp.repository;

import com.example.week7ecommerceapp.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
}
