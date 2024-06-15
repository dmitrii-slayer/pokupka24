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

    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC(12,2)")
    private BigDecimal price;

}