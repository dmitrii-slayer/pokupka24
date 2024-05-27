package org.akatsuki.pokupka24.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.akatsuki.pokupka24.mapper.PurchaseMapper;
import org.akatsuki.pokupka24.service.PurchaseService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Покупки", description = "Операции по покупкам")
@RequestMapping(PurchaseController.RESOURCE_PATH)
@RestController
public class PurchaseController {

    private static final String BASE_PATH = "/api/pokupka24";
    private static final String API_VERSION = "/v1";
    private static final String RESOURCE = "/purchases";

    public static final String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Operation(summary = "Поиск покупок")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PurchaseDTO>> findPurchases(@ParameterObject Pageable pageable) {
        Page<Purchase> purchases = purchaseService.findPurchases(pageable);
        return ResponseEntity.ok(new PageImpl<>(
                purchaseMapper.toDTOList(purchases.getContent()), pageable, purchases.getTotalElements()));
    }
}
