package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.model.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);
    List<Product> getAllProduct();
    Product getProductById(long id);
    void deleteProductById(long id);
}
