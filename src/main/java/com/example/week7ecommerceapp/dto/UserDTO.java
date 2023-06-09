package com.example.week7ecommerceapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private Long phoneNumber;

    private String address;
}
