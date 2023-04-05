package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Admin;

public interface AdminService {
    Admin findByEmail(String email);

    void save(AdminDTO adminDTO);

    void deleteProduct(long id);

    void updateProduct(long id, ProductDTO productDTO);

}
