package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, ProductRepositoryCustom {
}
