package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(ProductDTO productDTO);
    List<Product> getAllProduct();
    Product getProductById(Long id);
    void deleteProductById(Long id);
}
