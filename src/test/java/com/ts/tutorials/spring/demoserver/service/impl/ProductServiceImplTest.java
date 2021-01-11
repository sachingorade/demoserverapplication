package com.ts.tutorials.spring.demoserver.service.impl;

import com.ts.tutorials.spring.demoserver.jpa.ProductRepo;
import com.ts.tutorials.spring.demoserver.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void createProductWithRepository() {
        Product product = new Product(null, "TestProduct", "TestDescription", "TestModel", 10L);
        productService.createProduct(product);

        verify(productRepo).save(same(product));
    }

}