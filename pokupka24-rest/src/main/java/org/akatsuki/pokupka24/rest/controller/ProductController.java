package org.akatsuki.pokupka24.rest.controller;

import lombok.RequiredArgsConstructor;
import org.akatsuki.pokupka24.api.ProductApi;
import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.akatsuki.pokupka24.dto.ProductDTO;
import org.akatsuki.pokupka24.mapper.ProductMapper;
import org.akatsuki.pokupka24.service.ProductService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    public ResponseEntity<Page<ProductDTO>> findProducts(@ParameterObject Pageable pageable) {
        Page<Product> products = productService.findProducts(pageable);
        return ResponseEntity.ok(new PageImpl<>(
                productMapper.toDTOList(products.getContent()), pageable, products.getTotalElements()));
    }

    @Override
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("productId") UUID id) {
        return ResponseEntity.ok(productMapper.toDTO(productService.findProductById(id)));
    }

    @Override
    public ResponseEntity<Page<ProductDTO>> findProductsByCriteria(@RequestBody ProductCriteriaDTO criteriaDTO,
                                                                   @ParameterObject Pageable pageable) {
        Page<Product> products = productService.findProductsByCriteria(criteriaDTO, pageable);
        return ResponseEntity.ok(new PageImpl<>(
                productMapper.toDTOList(products.getContent()), pageable, products.getTotalElements()));
    }

    @Override
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.addProduct(productMapper.toEntity(productDTO));
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    @Override
    public ResponseEntity<ProductDTO> editProduct(@PathVariable("productId") UUID productId,
                                                  @RequestBody ProductDTO productDTO) {
        Product product = productService.editProduct(productId, productMapper.toEntity(productDTO));
        return ResponseEntity.ok(productMapper.toDTO(product));
    }
}
