package com.example.week7ecommerceapp.repository;

import com.example.week7ecommerceapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
