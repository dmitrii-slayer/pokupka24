package org.akatsuki.pokupka24.domain.repository;

import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseRepositoryCustom {
    Page<Purchase> findByCriteria(PurchaseCriteriaDTO criteriaDTO, Pageable pageable);
}
