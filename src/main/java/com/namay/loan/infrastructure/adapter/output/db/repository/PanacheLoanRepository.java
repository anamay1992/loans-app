package com.namay.loan.infrastructure.adapter.output.db.repository;

import com.namay.loan.infrastructure.adapter.output.db.entity.LoanEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PanacheLoanRepository implements PanacheRepository<LoanEntity> {

    public List<LoanEntity> findByDocument(String document) {
        return list("document", document);
    }
    public List<LoanEntity> findByClientId(Long clientId) {
        return list("client.id", clientId);
    }
}
