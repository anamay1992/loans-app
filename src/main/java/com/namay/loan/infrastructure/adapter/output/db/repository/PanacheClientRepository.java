package com.namay.loan.infrastructure.adapter.output.db.repository;

import com.namay.loan.infrastructure.adapter.output.db.entity.ClientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheClientRepository implements PanacheRepository<ClientEntity> {
    public ClientEntity findByDocument(String document) {
        return find("document", document).firstResult();
    }

    public void deleteByDocument(String document) {
        delete("document", document);
    }
}
