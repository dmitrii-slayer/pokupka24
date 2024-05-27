package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
