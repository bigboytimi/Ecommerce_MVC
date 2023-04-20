package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.model.Product;

public interface AdminService {
    Admin findByEmail(String email);

    Admin save(AdminDTO adminDTO);


    void deleteProduct(Long id);

    Product updateProduct(Long id, ProductDTO productDTO);

}
