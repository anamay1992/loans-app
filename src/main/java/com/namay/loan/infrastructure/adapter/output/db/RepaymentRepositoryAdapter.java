package com.namay.loan.infrastructure.adapter.output.db;

import com.namay.loan.domain.model.Repayment;
import com.namay.loan.domain.port.output.RepaymentRepository;
import com.namay.loan.infrastructure.adapter.output.db.entity.RepaymentEntity;
import com.namay.loan.infrastructure.adapter.output.db.mapper.LoanDbMapper;
import com.namay.loan.infrastructure.adapter.output.db.repository.PanacheRepaymentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RepaymentRepositoryAdapter implements RepaymentRepository {

    private final PanacheRepaymentRepository repaymentRepository;
    private final LoanDbMapper loanDbMapper;

    @Inject
    public RepaymentRepositoryAdapter(PanacheRepaymentRepository repaymentRepository,
                                      LoanDbMapper loanDbMapper) {
        this.repaymentRepository = repaymentRepository;
        this.loanDbMapper = loanDbMapper;
    }

    @Override
    public Repayment save(Repayment repayment) {
        RepaymentEntity entity = this.loanDbMapper.toRepaymentEntity(repayment);
        if (entity.getId() == null) {
            this.repaymentRepository.persist(entity);
        } else {
            entity = this.repaymentRepository.getEntityManager().merge(entity);
        }
        return this.loanDbMapper.toRepaymentDomain(entity);
    }
}
