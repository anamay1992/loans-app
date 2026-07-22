package com.namay.loan.infrastructure.adapter.output.db;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.port.output.InstallmentRepository;
import com.namay.loan.infrastructure.adapter.output.db.mapper.LoanDbMapper;
import com.namay.loan.infrastructure.adapter.output.db.repository.PanacheInstallmentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InstallmentRepositoryAdapter implements InstallmentRepository {

    private final PanacheInstallmentRepository installmentRepository;
    private final LoanDbMapper loanDbMapper;

    public InstallmentRepositoryAdapter(PanacheInstallmentRepository installmentRepository,
                                        LoanDbMapper loanDbMapper) {
        this.installmentRepository = installmentRepository;
        this.loanDbMapper = loanDbMapper;
    }

    @Override
    public List<Installment> findByLoanId(Long loanId) {
        return this.installmentRepository.findAllByLoanId(loanId).stream()
                .map(this.loanDbMapper::toInstallmentDomain)
                .toList();
    }
}
