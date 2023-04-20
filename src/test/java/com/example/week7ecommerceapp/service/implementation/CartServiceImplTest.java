package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.repository.CartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    Cart testCart;

    Cart testCart2;
    Cart randomCart;

    Cart testCartX;

    List<Cart> carts;
    List<Cart> cartsByUserId;
    @BeforeEach
    void setUp() {
        testCart = Cart.builder()
                .price(100.0)
                .id(1L)
                .product_id(2L)
                .quantity(90)
                .user_id(3L)
                .build();
        testCart2 = Cart.builder()
                .user_id(9L)
                .quantity(20)
                .price(200.0)
                .product_id(1L)
                .build();

        testCartX = Cart.builder()
                .price(20.0)
                .product_id(1L)
                .quantity(9)
                .user_id(3L)
                .build();

        randomCart = new Cart();

        carts = new ArrayList<>();
        carts.add(testCart);
        carts.add(testCart2);

        cartsByUserId = new ArrayList<>();
        cartsByUserId.add(testCartX);
        cartsByUserId.add(testCart);



    }

    @Test
    @DisplayName("Test to get product by Id")
    void getProductById() {
        when(cartRepository.findById(anyLong())).thenReturn(of(testCart));

        Cart cart = cartService.getProductById(1L);

        assertNotNull(cart);
        assertEquals(testCart, cart);
    }

    @Test
    void deleteProductById() {
        doNothing().when(cartRepository).deleteById(testCart.getId());

        cartService.deleteProductById(1L);

        verify(cartRepository, times(1)).deleteById(testCart.getId());
    }

    @Test
    void deleteCart() {
        doNothing().when(cartRepository).deleteAll();

        cartService.deleteCart();

        verify(cartRepository, times(1)).deleteAll();
    }

    @Test
    void getAllProduct() {
        when(cartRepository.findAll()).thenReturn(carts);

        List<Cart> checkCart = cartService.getAllProduct();

        assertEquals(checkCart, carts);
    }

    @Test
    void addToCart() {
        when(cartRepository.findByProductIdAndUserId(anyLong(), anyLong())).thenReturn(testCart2);
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart2);

        Cart expectedCart = cartService.addToCart(testCart2);

        assertEquals(expectedCart, testCart2);
        assertEquals(21, expectedCart.getQuantity());
    }



    @Test
    void removeFromCartById() {
        doNothing().when(cartRepository).deleteById(anyLong());

        cartService.removeFromCartById(testCart.getId());

        verify(cartRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getCartItemsByUserId() {
        when(cartRepository.findByUser_id(anyLong())).thenReturn(cartsByUserId);

        List<Cart> cartList = cartService.getCartItemsByUserId(3L);

        assertEquals(cartsByUserId, cartList);
    }

    @Test
    void getCartItemByProductId() {
        when(cartRepository.findByProductId(anyLong())).thenReturn(testCartX);


        Cart expectedCart = cartService.getCartItemByProductId(2L);
        assertEquals(expectedCart, testCartX);
    }
}