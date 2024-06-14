package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.domain.repository.ProductRepository;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.akatsuki.pokupka24.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("No product with ID: " + productId));
    }

    @Override
    public Page<Product> findProductsByCriteria(ProductCriteriaDTO criteriaDTO, Pageable pageable) {
        return productRepository.findByCriteria(criteriaDTO, pageable);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(UUID productId, Product product) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new NotFoundException("No product with ID: " + productId);
        }
        product.setProductId(productId);
        return productRepository.save(product);
    }
}
