package com.ts.tutorials.spring.demoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "products_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public Product(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 128)
    @NotEmpty
    private String name;

    @Column(length = 512)
    @NotEmpty
    private String description;

    @Column(length = 128, unique = true)
    @NotEmpty
    private String model;

    @Column
    @PositiveOrZero
    private Long quantity;

}
