package com.example.week7ecommerceapp.repository;

import com.example.week7ecommerceapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmail(String email);
}
