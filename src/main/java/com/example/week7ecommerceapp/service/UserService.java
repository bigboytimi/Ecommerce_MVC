package com.example.week7ecommerceapp.service;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.User;

import java.util.List;

public interface  UserService {
    User addUser(UserDTO userDTO);
    User getUserById(long id);
    void deleteUser(User user);
    List<User> findAllUsers();
    User validateUser(String email, String password);
    User findByEmail(String email);
}
