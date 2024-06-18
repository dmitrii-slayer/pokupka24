package org.akatsuki.pokupka24.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.akatsuki.pokupka24.dto.ProductDTO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Товары", description = "Операции по товарам")
//@RequestMapping(ProductApi.RESOURCE_PATH)
public interface ProductApi {
    String BASE_PATH = "/api/pokupka24";
    String API_VERSION = "/v1";
    String RESOURCE = "/products";
    String SEARCH = "/search";
    String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    @Operation(summary = "Поиск товаров")
    @GetMapping(value = RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<ProductDTO>> findProducts(@ParameterObject Pageable pageable);

    @Operation(summary = "Поиск товара по ID")
    @GetMapping(value = RESOURCE_PATH + "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> findProductById(@PathVariable("productId") UUID id);

    @Operation(summary = "Поиск товаров по критериям")
    @PostMapping(value = RESOURCE_PATH + SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<ProductDTO>> findProductsByCriteria(@RequestBody ProductCriteriaDTO criteriaDTO,
                                                            @ParameterObject Pageable pageable);

    @Operation(summary = "Добавление товара")
    @PostMapping(value = RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO);

    @Operation(summary = "Редактирование товара")
    @PutMapping(value = RESOURCE_PATH + "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> editProduct(@PathVariable("productId") UUID productId,
                                           @RequestBody ProductDTO productDTO);
}
