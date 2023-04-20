package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.repository.ProductRepository;
import com.example.week7ecommerceapp.repository.UserRepository;
import com.example.week7ecommerceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    @Autowired
    private final UserRepository repo;

    @Autowired
    private final ProductRepository productRepository;

    HashMap<Long, Product> tempCart = new HashMap<>();

    @Override
    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        repo.save(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser = repo.findById(id);
        User user1 = null;
        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
            return user1;
        } else{
            return user1;
//            throw new RuntimeException("User with " + id + "not found!");
        }

    }

    @Override
    public void deleteUser(User user) {
        repo.delete(user);
    }



    @Override
    public List<User> findAllUsers() {
        return (List<User>) repo.findAll();
    }

    @Override
    public User validateUser(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByEmail(String email) {
        User user = repo.findByEmail(email).orElse(null);
        if(user == null){
            return null;
        } else{
            return user;
        }
    }
}
