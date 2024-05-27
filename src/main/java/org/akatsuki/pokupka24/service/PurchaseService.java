package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {

    Page<Purchase> findPurchases(Pageable pageable);
}
