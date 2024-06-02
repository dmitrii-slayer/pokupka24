package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "title", nullable = false)
    private String title;

    // наверно можно избавиться от этого столбца
    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

}