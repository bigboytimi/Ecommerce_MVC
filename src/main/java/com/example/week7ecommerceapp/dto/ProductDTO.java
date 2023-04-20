package com.example.week7ecommerceapp.dto;


import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.model.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private String productName;
    private String category;
    private int quantity;
    private String description;
    private double price;
}
