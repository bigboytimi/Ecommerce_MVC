package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.repository.ProductRepository;
import com.example.week7ecommerceapp.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    ProductDTO productDTO;
    ProductDTO productDTO1;
    Product product;
    Product product1;
    List<Product> products;

    @BeforeEach
    void setUp() {
        productDTO = ProductDTO.builder()
                .productName("Milk")
                .category("BEVERAGE")
                .quantity(20)
                .price(100.0)
                .build();

        productDTO1 = ProductDTO.builder()
                .productName("Yam")
                .price(200.0)
                .quantity(20)
                .category("Food")
                .build();

        product = new Product(productDTO);
        product1 = new Product(productDTO1);
        product.setId(1L);
        product1.setId(2L);

        products = new ArrayList<>();
        products.add(product);
        products.add(product1);


    }


    @Test
    void saveProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product product1 = productService.saveProduct(productDTO);

        assertNotNull(product1);
        assertEquals("Milk", product1.getProductName());
    }

    @Test
    void getAllProduct() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> productList = productService.getAllProduct();

        assertNotNull(productList);
        assertEquals(products, productList);
    }

    @Test
    void getProductById() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product1));

        Product wantedProduct = productService.getProductById(product1.getId());

        assertNotNull(wantedProduct);
        assertEquals(wantedProduct, product1);
    }

    @Test
    void deleteProductById() {
        doNothing().when(productRepository).deleteById(product1.getId());

        productService.deleteProductById(product1.getId());

        verify(productRepository, times(1)).deleteById(product1.getId());
    }
}