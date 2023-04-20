package com.example.week7ecommerceapp.model;

import com.example.week7ecommerceapp.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "wishlist")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wishlist {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long product_id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String price;

}
