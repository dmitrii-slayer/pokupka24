package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//@Entity
@Getter
@Setter
public class PurchaseDetail {

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "id", nullable = false)
    private UUID id;
//
//    @ManyToOne
//    @JoinColumn(name = "purchase_id", referencedColumnName = "purchase_id")
    private Purchase purchase;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
