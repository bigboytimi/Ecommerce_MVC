package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.repository.ProductRepository;
import com.example.week7ecommerceapp.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository prodRepo;
    @Override
    public void saveProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        prodRepo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return prodRepo.findAll();
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> optionalProduct = prodRepo.findById(id);
        Product testProduct = null;
        if(optionalProduct.isPresent()){
            testProduct = optionalProduct.get();
        } else{
            throw new RuntimeException("Product with " + id + " not found");
        }
        return testProduct;
    }

    @Override
    public void deleteProductById(long id) {
        prodRepo.deleteById(id);
    }
}
