package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    Page<Product> findProducts(Pageable pageable);

    Product findProductById(UUID productId);
}
