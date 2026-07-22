package com.namay.loan.infrastructure.adapter.output.db.repository;

import com.namay.loan.infrastructure.adapter.output.db.entity.RepaymentEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheRepaymentRepository implements PanacheRepository<RepaymentEntity> {
}
