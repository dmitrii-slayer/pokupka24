package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @Size(max = 120)
    @Column(name = "title", nullable = false)
    private String title;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @PositiveOrZero(message = "Price can't be less than 0!")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

}