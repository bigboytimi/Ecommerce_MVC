package com.example.week7ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private String firstName;

    private String lastName;
    private String email;
    private String password;
}
