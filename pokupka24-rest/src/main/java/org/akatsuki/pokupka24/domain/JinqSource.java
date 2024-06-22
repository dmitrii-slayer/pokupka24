package org.akatsuki.pokupka24.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import org.akatsuki.pokupka24.domain.entity.Product;
import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.stereotype.Component;

@Component
public class JinqSource {

    private JinqJPAStreamProvider streams;

    @PersistenceUnit
    public void setEntityManagerFactory(
            EntityManagerFactory emf) {
        streams = new JinqJPAStreamProvider(emf);
    }

    public <U> JPAJinqStream<U> streamAll(
            EntityManager em, Class<U>entity) {
        return streams.streamAll(em, entity);
    }

    public JPAJinqStream<Product> products(EntityManager em) {
        return streams.streamAll(em, Product.class);
    }
}
