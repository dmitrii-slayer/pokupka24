package org.akatsuki.pokupka24.service;

import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public Page<Purchase> findPurchases(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }
}
