package org.akatsuki.pokupka24.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Repository
public class PurchaseRepositoryCustomImpl implements PurchaseRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public Page<Purchase> findByCriteria(PurchaseCriteriaDTO criteriaDTO, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Purchase> query = cb.createQuery(Purchase.class);
        Root<Purchase> root = query.from(Purchase.class);

        List<Predicate> predicates = new ArrayList<>();

        if (!CollectionUtils.isEmpty(criteriaDTO.getAccountIdIn())) {
            predicates.add(root.get("userAccount").<UUID>get("accountId")
                    .in(criteriaDTO.getAccountIdIn()));
        }
        if (!CollectionUtils.isEmpty(criteriaDTO.getPurchaseIdIn())) {
            predicates.add(root.get("purchaseId").in(criteriaDTO.getPurchaseIdIn()));
        }
        if (!CollectionUtils.isEmpty(criteriaDTO.getProductIdIn())) {
            predicates.add(root.get("products").get("productId").in(criteriaDTO.getProductIdIn()));
        }
        if (criteriaDTO.getCreatedAfter() != null) {
            predicates.add(cb.greaterThan(root.get("createdAt"), criteriaDTO.getCreatedAfter()));
        }
        if (criteriaDTO.getCreatedBefore() != null) {
            predicates.add(cb.lessThan(root.get("createdAt"), criteriaDTO.getCreatedBefore()));
        }

        query.select(root).where(predicates.toArray(Predicate[]::new));
        query.orderBy(QueryUtils.toOrders(pageable.getSort(), root, cb));
        TypedQuery<Purchase> purchaseQuery = em.createQuery(query);
        List<Purchase> purchases = purchaseQuery.getResultList();
        return new PageImpl<>(purchases, pageable, purchases.size());
    }
}
