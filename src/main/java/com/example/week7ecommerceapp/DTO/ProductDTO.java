package com.example.week7ecommerceapp.DTO;


import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.model.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductDTO {
    private String productName;
    private ProductCategory category;
    private BigDecimal price;
    public ProductDTO(Product product){
        this.productName = product.getProductName();
        this.category = product.getCategory();
        this.price = product.getProductPrice();
    }
}
