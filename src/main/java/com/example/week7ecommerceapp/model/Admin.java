package com.example.week7ecommerceapp.model;


import com.example.week7ecommerceapp.dto.AdminDTO;
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
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName;

    private String password;

    @Column(unique = true)
    private String email;

    public Admin(AdminDTO adminDTO) {
        this.firstName = adminDTO.getFirstName();
        this.lastName = adminDTO.getLastName();
        this.password = adminDTO.getPassword();
        this.email = adminDTO.getEmail();
    }
}
