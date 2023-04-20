package com.example.week7ecommerceapp.model;

import com.example.week7ecommerceapp.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private double price;

    public Product(ProductDTO productDTO){
        this.productName = productDTO.getProductName();
        this.category = productDTO.getCategory();
        this.quantity = productDTO.getQuantity();
        this.description = productDTO.getDescription();
        this.price = productDTO.getPrice();

    }
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Cart cart;
}


