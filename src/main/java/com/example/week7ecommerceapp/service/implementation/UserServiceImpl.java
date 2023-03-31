package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.repository.UserRepository;
import com.example.week7ecommerceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    @Autowired
    private final UserRepository repo;

    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        repo.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> optionalUser = repo.findById(id);
        User user1 = null;
        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
        } else{
            throw new RuntimeException("User with " + id + "not found!");
        }
        return user1;
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
