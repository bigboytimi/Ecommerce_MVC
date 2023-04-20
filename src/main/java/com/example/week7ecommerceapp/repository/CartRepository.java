package com.example.week7ecommerceapp.repository;

import com.example.week7ecommerceapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

   @Query(value = "SELECT * FROM cart WHERE product_id = ?1 AND user_id = ?2", nativeQuery = true)
   Cart findByProductIdAndUserId(Long productId, Long userId);
   @Query(value = "SELECT * FROM cart WHERE user_id = ?1", nativeQuery = true)
   List<Cart> findByUser_id(Long user_id);
   @Query(value = "SELECT * FROM cart WHERE product_id = ?1", nativeQuery = true)
   Cart findByProductId(Long id);
}
