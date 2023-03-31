package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.model.Admin;

public interface AdminService {
    Admin findByEmail(String email);
    void addAdmin(AdminDTO adminDTO);

}
