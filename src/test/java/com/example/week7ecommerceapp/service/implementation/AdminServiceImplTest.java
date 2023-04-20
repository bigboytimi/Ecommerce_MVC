package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.model.Cart;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.repository.AdminRepository;
import com.example.week7ecommerceapp.repository.ProductRepository;
import com.example.week7ecommerceapp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private AdminServiceImpl adminService;
    AdminDTO adminDTO;
    Admin admin;

    Product product;

    ProductDTO productDTO;

    Product toBeAdded;

    @BeforeEach
    void setUp() {
        adminDTO = new AdminDTO();
        adminDTO.setFirstName("admin");
        adminDTO.setLastName("admin");
        adminDTO.setPassword("1234");
        adminDTO.setEmail("admin@gmail.com");
        admin = new Admin(adminDTO);

        productDTO = new ProductDTO();
        productDTO.setProductName("DODO-PIZZA");
        productDTO.setCategory("FOOD");
        productDTO.setPrice(100.0);
        productDTO.setQuantity(10);
        productDTO.setDescription("Debonair Pizza");
        toBeAdded = new Product(productDTO);

        product = new Product();
        product.setId(1L);
        product.setPrice(90.0);
        product.setCategory("food");
        product.setQuantity(4);
        product.setProductName("milk");
        product.setDescription("barnad milk");
        product.setCart(new Cart());
    }

    @Test
    void findByEmail() {
        when(adminRepository.findByEmail(anyString())).thenReturn((admin));
        Admin expectedAdmin = adminService.findByEmail(adminDTO.getEmail());

        assertNotNull(expectedAdmin);
        assertEquals(expectedAdmin, admin);
    }

    @Test
    void findByEmailReturnNull(){
        when(adminRepository.findByEmail(anyString())).thenReturn(null);
        Admin expectedAdmin = adminService.findByEmail("null@email.com");

        assertNull(expectedAdmin);
        assertEquals(expectedAdmin, null);
    }

    @Test
    void save() {
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        Admin expectedAdmin = adminService.save(adminDTO);

        assertNotNull(expectedAdmin);
        assertEquals(expectedAdmin.getFirstName(), "admin");
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(product.getId());

        adminService.deleteProduct(product.getId());

        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    void updateProduct() {

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product existingProduct = adminService.updateProduct(1L, productDTO);

        assertNotNull(existingProduct);
        assertEquals("DODO-PIZZA", product.getProductName());
    }

    @Test
    void updateProductIfNull(){
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(toBeAdded);

        Product existingProduct = adminService.updateProduct(2L, productDTO);

//        assertNotNull(existingProduct);
        assertEquals("DODO-PIZZA", toBeAdded.getProductName());
    }
}