package com.example.week7ecommerceapp.model;

import com.example.week7ecommerceapp.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String price;
    @Column(nullable = false)
    private String quantity;

    public Cart(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
    }
}
