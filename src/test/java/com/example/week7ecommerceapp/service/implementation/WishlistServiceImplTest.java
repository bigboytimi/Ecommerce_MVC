package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.model.Wishlist;
import com.example.week7ecommerceapp.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    Wishlist wishlist;
    Wishlist wishlistX;

    List<Wishlist> wishlistList;

    @BeforeEach
    void setUp() {
        wishlist = Wishlist.builder()
                .description("Nice Biscuits")
                .id(1L)
                .price("20")
                .productName("Biscuits")
                .build();

        wishlistX = Wishlist.builder()
                .productName("Cookies")
                .product_id(9L)
                .price("50")
                .id(1L)
                .description("Nice cookies").build();

        wishlistList = new ArrayList<>();
        wishlistList.add(wishlistX);
        wishlistList.add(wishlist);
    }

    @Test
    void getAllWishlist() {
        when(wishlistRepository.findAll()).thenReturn(wishlistList);

        List<Wishlist> wishlists = wishlistService.getAllWishlist();

        assertEquals(wishlistList, wishlists);
    }

    @Test
    void addWishlist() {
        when(wishlistRepository.save(any(Wishlist.class))).thenReturn(wishlistX);

        Wishlist expectedWishlist = wishlistService.addWishlist(wishlistX);

        assertEquals(expectedWishlist, wishlistX);
    }

    @Test
    void removeWishList() {
        doNothing().when(wishlistRepository).deleteById(anyLong());
        wishlistService.removeWishList(anyLong());
        verify(wishlistRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getWishlistByProductId() {
        when(wishlistRepository.findByProductId(anyLong())).thenReturn(wishlist);
        Wishlist expectedWishlist = wishlistService.getWishlistByProductId(anyLong());

        assertEquals(expectedWishlist, wishlist);
    }
}