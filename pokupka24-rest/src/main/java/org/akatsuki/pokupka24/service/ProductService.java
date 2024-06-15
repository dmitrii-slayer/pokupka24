package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {

    Page<Product> findProducts(Pageable pageable);

    Product findProductById(UUID productId);

    Page<Product> findProductsByCriteria(ProductCriteriaDTO criteriaDTO, Pageable pageable);

    Product addProduct(Product product);

    Product editProduct(UUID productId, Product product);
}
