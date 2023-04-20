package com.example.week7ecommerceapp.repository;

import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query(value= "SELECT * FROM wishlist WHERE product_id = ?1", nativeQuery = true)
    Wishlist findByProductId(Long id);

}
