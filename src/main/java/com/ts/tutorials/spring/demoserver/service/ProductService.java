package com.ts.tutorials.spring.demoserver.service;

import com.ts.tutorials.spring.demoserver.model.Product;

import java.util.List;

public interface ProductService {

    Product getById(Long id);

    List<Product> getAll();

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

}
