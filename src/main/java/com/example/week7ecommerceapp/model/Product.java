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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productName;

    public Product(ProductDTO productDTO){
        this.productName = productDTO.getProductName();
        this.category = productDTO.getCategory();
        this.description = productDTO.getDescription();
        this.productPrice = productDTO.getPrice();
    }
    @Column(nullable = false)
    private long serialNum;

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal productPrice;

    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private int units;
    private String image;

    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Cart cart;
}


