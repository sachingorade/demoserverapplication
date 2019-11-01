package com.ts.tutorials.spring.demoserver.service.impl;

import com.ts.tutorials.spring.demoserver.jpa.ProductRepo;
import com.ts.tutorials.spring.demoserver.model.Product;
import com.ts.tutorials.spring.demoserver.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        System.out.println("Creating product:" + product);
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }

}
