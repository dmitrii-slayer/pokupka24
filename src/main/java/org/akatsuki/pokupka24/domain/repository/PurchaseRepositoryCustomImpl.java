package org.akatsuki.pokupka24.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.akatsuki.pokupka24.domain.entity.Purchase;
import org.akatsuki.pokupka24.dto.PurchaseCriteriaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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
//        if (!CollectionUtils.isEmpty(criteriaDTO.getAccountIdIn())) {
//        }
        query.select(root).where(root.get("userAccount").<UUID>get("accountId")
                .in(criteriaDTO.getAccountIdIn()));
        TypedQuery<Purchase> purchaseQuery = em.createQuery(query);
        List<Purchase> purchases = purchaseQuery.getResultList();
        return new PageImpl<>(purchases, pageable, purchases.size());
    }
}
