package com.example.week7ecommerceapp.model;

import com.example.week7ecommerceapp.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "product_Id", nullable = false)
    private Long product_id;
    @JoinColumn(name = "user_id", nullable = false)
    private Long user_id;
    @Column(name = "product_price", nullable = false)
    private Double price;
    @Column(nullable = false)
    private int quantity;

}
