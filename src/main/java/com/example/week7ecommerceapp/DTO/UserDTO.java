package com.example.week7ecommerceapp.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private long phoneNumber;

    private String address;
}
