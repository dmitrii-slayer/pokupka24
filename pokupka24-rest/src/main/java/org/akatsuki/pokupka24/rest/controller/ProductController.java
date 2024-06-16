package org.akatsuki.pokupka24.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.akatsuki.pokupka24.dto.ProductDTO;
import org.akatsuki.pokupka24.mapper.ProductMapper;
import org.akatsuki.pokupka24.service.ProductService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Товары", description = "Операции по товарам")
@RequestMapping(ProductController.RESOURCE_PATH)
@RestController
@RequiredArgsConstructor
public class ProductController {

    public static final String BASE_PATH = "/api/pokupka24";
    public static final String API_VERSION = "/v1";
    public static final String RESOURCE = "/products";
    public static final String SEARCH = "/search";

    public static final String RESOURCE_PATH = BASE_PATH + API_VERSION + RESOURCE;

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Поиск товаров")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductDTO>> findProducts(@ParameterObject Pageable pageable) {
        Page<Product> products = productService.findProducts(pageable);
        return ResponseEntity.ok(new PageImpl<>(
                productMapper.toDTOList(products.getContent()), pageable, products.getTotalElements()));
    }

    @Operation(summary = "Поиск товара по ID")
    @GetMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("productId") UUID id) {
        return ResponseEntity.ok(productMapper.toDTO(productService.findProductById(id)));
    }

    @Operation(summary = "Поиск товаров по критериям")
    @PostMapping(value = SEARCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProductDTO>> findProductsByCriteria(@RequestBody ProductCriteriaDTO criteriaDTO,
                                                                   @ParameterObject Pageable pageable) {
        Page<Product> products = productService.findProductsByCriteria(criteriaDTO, pageable);
        return ResponseEntity.ok(new PageImpl<>(
                productMapper.toDTOList(products.getContent()), pageable, products.getTotalElements()));
    }

    @Operation(summary = "Добавление товара")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productMapper.toEntity(productDTO));
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @Operation(summary = "Редактирование товара")
    @PutMapping(value = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> editProduct(@PathVariable("productId") UUID productId,
                                                  @RequestBody ProductDTO productDTO) {
        Product product = productService.editProduct(productId, productMapper.toEntity(productDTO));
        return ResponseEntity.ok(productMapper.toDTO(product));
    }
}
