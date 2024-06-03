package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<Product> findByCriteria(ProductCriteriaDTO criteriaDTO, Pageable pageable);
}
