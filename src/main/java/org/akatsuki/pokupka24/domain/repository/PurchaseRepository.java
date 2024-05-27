package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
}
