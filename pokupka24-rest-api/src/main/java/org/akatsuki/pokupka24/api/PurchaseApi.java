package org.akatsuki.pokupka24.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.akatsuki.pokupka24.dto.PurchaseCriteriaDTO;
import org.akatsuki.pokupka24.dto.PurchaseDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Покупки", description = "Операции по покупкам")
//@RequestMapping(PurchaseApi.RESOURCE_PATH)
public interface PurchaseApi {

    String BASE_PATH = "/api/pokupka24";
    String API_VERSION = "/v1";
    String RESOURCE = "/purchases";
    String SEARCH = "/search";
    String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    @Operation(summary = "Поиск покупок")
    @GetMapping(value = RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<PurchaseDTO>> findPurchases(@ParameterObject Pageable pageable);

    @Operation(summary = "Поиск покупок по критериям")
    @PostMapping(value = RESOURCE_PATH + SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<PurchaseDTO>> findPurchasesByCriteria(
            @RequestBody PurchaseCriteriaDTO criteriaDTO,
            @ParameterObject Pageable pageable);

    @Operation(summary = "Добавить покупку")
    @PostMapping(value = RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PurchaseDTO> addPurchase(@RequestBody PurchaseDTO purchaseDTO);

    @Operation(summary = "Получение покупок пользователя")
    @GetMapping(RESOURCE_PATH + "/{userId}/purchases")
    ResponseEntity<List<PurchaseDTO>> findUserPurchases(@PathVariable("userId") UUID userId);
}
