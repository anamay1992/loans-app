package com.namay.loan.infrastructure.adapter.output.db.repository;

import com.namay.loan.infrastructure.adapter.output.db.entity.InstallmentEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PanacheInstallmentRepository implements PanacheRepository<InstallmentEntity> {

    public List<InstallmentEntity> findAllByLoanId(Long loanId) {
        return list("loanId", loanId);
    }
}
