package com.ts.tutorials.spring.demoserver.jpa;

import com.ts.tutorials.spring.demoserver.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findAll();

}
