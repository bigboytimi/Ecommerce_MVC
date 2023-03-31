package com.example.week7ecommerceapp.dto;


import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.model.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String productName;
    private String category;

    private int quantity;

    private String description;
    private BigDecimal price;
    public ProductDTO(Product product){
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getProductPrice();
    }
}
