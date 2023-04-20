package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.AdminDTO;
import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Admin;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.repository.AdminRepository;
import com.example.week7ecommerceapp.repository.ProductRepository;
import com.example.week7ecommerceapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private ProductRepository productRepository;

    public AdminServiceImpl(AdminRepository adminRepo, ProductRepository productRepository) {
        this.adminRepo = adminRepo;
        this.productRepository = productRepository;
    }

    @Override
    public Admin findByEmail(String email) {
        Admin admin = adminRepo.findByEmail(email);
        if (admin == null){
            return null;
        } else{
            return admin;
        }
    }

    @Override
    public Admin save(AdminDTO adminDTO) {
        Admin admin = new Admin(adminDTO);
        adminRepo.save(admin);
        return admin;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        System.out.println("Product successfully deleted");
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null){
            Product toBeAdded = new Product(productDTO);
            productRepository.save(toBeAdded);
            return toBeAdded;
        } else{
            product.setProductName(productDTO.getProductName());
            product.setQuantity(productDTO.getQuantity());
            product.setDescription(productDTO.getDescription());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            productRepository.save(product);
        }
        return product;
    }




}
