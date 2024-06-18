package org.akatsuki.pokupka24.rest.controller;

import lombok.RequiredArgsConstructor;
import org.akatsuki.pokupka24.api.PurchaseApi;
import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseCriteriaDTO;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.akatsuki.pokupka24.mapper.PurchaseMapper;
import org.akatsuki.pokupka24.service.PurchaseService;
import org.akatsuki.pokupka24.service.UserService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PurchaseController implements PurchaseApi {

    private final PurchaseService purchaseService;
    private final PurchaseMapper purchaseMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<Page<PurchaseDTO>> findPurchases(@ParameterObject Pageable pageable) {
        Page<Purchase> purchases = purchaseService.findPurchases(pageable);
        return ResponseEntity.ok(new PageImpl<>(
                purchaseMapper.toDTOList(purchases.getContent()), pageable, purchases.getTotalElements()));
    }

    @Override
    public ResponseEntity<Page<PurchaseDTO>> findPurchasesByCriteria(
            @RequestBody PurchaseCriteriaDTO criteriaDTO,
            @ParameterObject Pageable pageable) {
        Page<Purchase> purchases = purchaseService.findPurchasesByCriteria(criteriaDTO, pageable);
        return ResponseEntity.ok(new PageImpl<>(
                purchaseMapper.toDTOList(purchases.getContent()), pageable, purchases.getTotalElements()));
    }

    @Override
    public ResponseEntity<PurchaseDTO> addPurchase(@RequestBody PurchaseDTO purchaseDTO) {
        Purchase purchase = purchaseService.addPurchase(purchaseMapper.toEntity(purchaseDTO));
        return ResponseEntity.ok(purchaseMapper.toDTO(purchase));
    }

    @Override
    public ResponseEntity<List<PurchaseDTO>> findUserPurchases(@PathVariable("userId") UUID userId) {
        Set<Purchase> userPurchases = userService.findUserAccount(userId).getPurchases();
        return ResponseEntity.ok(purchaseMapper.toDTOList(List.copyOf(userPurchases)));
    }
}
