package com.example.week7ecommerceapp.model;

import com.example.week7ecommerceapp.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private long phoneNumber;

    private String address;

    public User(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.address = userDTO.getAddress();
    }
}
