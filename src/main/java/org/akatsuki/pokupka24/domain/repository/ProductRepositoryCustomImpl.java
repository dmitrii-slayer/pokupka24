package org.akatsuki.pokupka24.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.akatsuki.pokupka24.domain.JinqSource;
import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.dto.ProductCriteriaDTO;
import org.apache.commons.lang3.StringUtils;
import org.jinq.jpa.JPQL;
import org.jinq.orm.stream.JinqStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.jinq.jpa.JPQL.like;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private JinqSource source;

    @Override
    public Page<Product> findByCriteria(ProductCriteriaDTO criteriaDTO, Pageable pageable) {
        JinqStream<Product> products = source.products(em);
        if (!StringUtils.isEmpty(criteriaDTO.getTitleLike())) {
            String titleLike = criteriaDTO.getTitleLike();
            // если в лямбде написать criteriaDTO.getTitleLike() то не будет работать
            products = products.where(p -> like(p.getTitle(), titleLike));
        }
        if (!StringUtils.isEmpty(criteriaDTO.getDescriptionLike())) {
            String descriptionLike = "%" + criteriaDTO.getDescriptionLike() + "%";
            products = products.where(p -> like(p.getDescription(), descriptionLike));
        }
        Set<UUID> productIdIn = criteriaDTO.getProductIdIn();
        // вариант 1
//        if (!CollectionUtils.isEmpty(productIdIn)) {
//            products = products.where(p -> productIdIn.contains(p.getProductId()));
//        }
        // вариант 2
//        if (!CollectionUtils.isEmpty(productIdIn)) {
//            products = products.where(p -> JPQL.listContains(productIdIn, p.getProductId()));
//        }
        // вариант 3
        if (!CollectionUtils.isEmpty(productIdIn)) {
            products = products.where(p -> JPQL.isInList(p.getProductId(), productIdIn));
        }
        BigDecimal priceMoreThan = criteriaDTO.getPriceMoreThan();
        if (priceMoreThan != null) {
            products = products.where(p -> p.getPrice().compareTo(priceMoreThan) >= 0);
        }
        BigDecimal priceLessThan = criteriaDTO.getPriceLessThan();
        if (priceLessThan != null) {
            products = products.where(p -> p.getPrice().compareTo(priceLessThan) <= 0);
        }
        List<Product> productList = products.toList();
        return new PageImpl<>(productList, pageable, productList.size());
    }
}
