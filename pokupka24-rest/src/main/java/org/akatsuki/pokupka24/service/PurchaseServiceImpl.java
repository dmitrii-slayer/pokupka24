package org.akatsuki.pokupka24.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.domain.repository.PurchaseRepository;
import org.akatsuki.pokupka24.dto.PurchaseCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@FieldDefaults(makeFinal = true)
//@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Override
    public Page<Purchase> findPurchases(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    public Page<Purchase> findPurchasesByCriteria(PurchaseCriteriaDTO criteriaDTO, Pageable pageable) {
        return purchaseRepository.findByCriteria(criteriaDTO, pageable);
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        purchase.setCreatedAt(LocalDateTime.now());
        return purchaseRepository.save(purchase);
    }
}
