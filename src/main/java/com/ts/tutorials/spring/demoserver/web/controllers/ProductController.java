package com.ts.tutorials.spring.demoserver.web.controllers;

import com.ts.tutorials.spring.demoserver.model.Product;
import com.ts.tutorials.spring.demoserver.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    List<Product> listProducts() {
        return productService.getAll();
    }

    @PostMapping
    Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    Product updateProduct(@NotNull @PathVariable("productId") Long productId, @RequestBody Product product) {
        Product productById = productService.getById(productId);
        product.setId(productId);
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    void deleteProduct(@NotNull @PathVariable("productId") Long productId) {
        productService.deleteProduct(new Product(productId));
    }

}
