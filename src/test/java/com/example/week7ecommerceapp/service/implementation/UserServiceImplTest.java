package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.UserDTO;
import com.example.week7ecommerceapp.model.User;
import com.example.week7ecommerceapp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserDTO userDTO;

    UserDTO userDTOX;
    @Mock
    User randomUser;

    User randomUserX;
    List<User> users;

    @BeforeEach
    void setUp() {
        userDTO = UserDTO.builder()
                .firstName("Daro")
                .lastName("Bida")
                .password("1029")
                .phoneNumber(900L)
                .email("darobida@gmail.com")
                .address("lokoja rd 4").
                build();

        userDTOX = UserDTO.builder()
                .firstName("John")
                .lastName("Cena")
                .password("0929")
                .phoneNumber(1000L)
                .email("johncena@gmail.com")
                .address("abraka rd 4").
                build();



        randomUser = new User(userDTO);
        randomUserX = new User(userDTOX);

        users = new ArrayList<>();
        users.add(randomUserX);
        users.add(randomUser);


    }

    @Test
    void addUser() {
        when(repository.save(any(User.class))).thenReturn(randomUser);

        User user = userService.addUser(userDTO);

        assertNotNull(user);
        assertEquals("Daro", user.getFirstName());
        assertEquals("lokoja rd 4", user.getAddress());
    }

    @Test
    void getUserById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(randomUser));

        User user = userService.getUserById(89);

        assertEquals(randomUser, user);
    }

    @Test
    void getUserByIdIfUserIsNull(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        User user = userService.getUserById(29);

        assertNull(user);
        assertEquals(user, null);
    }

    @Test
    void deleteUser() {
        doNothing().when(repository).delete(any(User.class));

        userService.deleteUser(randomUser);

        verify(repository, times(1)).delete(randomUser);
    }

    @Test
    void findAllUsers() {
        when(repository.findAll()).thenReturn(users);

        List<User> userList = userService.findAllUsers();

        assertEquals(users, userList);

    }

    @Test
    void validateUser() {
        when(repository.findByEmailAndPassword("johncena@gmail.com", "0129")).thenReturn(randomUserX);

        User user = userService.validateUser("johncena@gmail.com", "0129");

        assertEquals(randomUserX, user);
    }

    @Test
    void findByEmail() {
        when(repository.findByEmail("darobida@gmail.com")).thenReturn(Optional.of(randomUser));

        User user = userService.findByEmail(randomUser.getEmail());

        assertEquals(randomUser, user);
    }

    @Test
    void findByEmailIfNull(){
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());

        User user = userService.findByEmail("random@gmail.com");
        assertNull(user);
    }

}