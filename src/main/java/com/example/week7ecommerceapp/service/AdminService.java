package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.model.Admin;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findByEmail(String email);
    void addAdmin(AdminDTO adminDTO);

}
