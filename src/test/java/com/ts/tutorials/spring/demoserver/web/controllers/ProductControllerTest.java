package com.ts.tutorials.spring.demoserver.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.tutorials.spring.demoserver.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.StreamUtils.copyToString;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:mocks/products/new-product.json")
    private Resource newProductJsonResource;

    @Test
    void createNewProduct() throws Exception {
        MvcResult result = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(copyToString(newProductJsonResource.getInputStream(), StandardCharsets.UTF_8))
                .header(HttpHeaders.AUTHORIZATION, "Basic c2FjaGluOnBhc3N3b3Jk")
        ).andExpect(status().isOk())
        .andReturn();

        Product product = objectMapper.readValue(result.getResponse().getContentAsString(), Product.class);
        assertNotNull(product.getId());
    }

}