package com.example.week7ecommerceapp.service.implementation;

import com.example.week7ecommerceapp.dto.ProductDTO;
import com.example.week7ecommerceapp.model.Product;
import com.example.week7ecommerceapp.repository.ProductRepository;
import com.example.week7ecommerceapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository prodRepo;
    @Override
    public Product saveProduct(ProductDTO productDTO) {
        Product product = new Product(productDTO);
        prodRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getAllProduct() {
        return prodRepo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
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
    public void deleteProductById(Long id) {
        prodRepo.deleteById(id);
    }
}
