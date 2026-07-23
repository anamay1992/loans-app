package com.namay.loan.application.usecase;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.port.input.InstallmentUseCase;
import com.namay.loan.domain.port.output.InstallmentRepository;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class InstallmentUseCaseImpl implements InstallmentUseCase {

    private final InstallmentRepository installmentRepository;

    public InstallmentUseCaseImpl(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @Override
    @Transactional
    @CacheResult(cacheName = "installments-by-loan-id-cache")
    public List<Installment> getInstallmentsByLoanId(Long loanId) {
        return this.installmentRepository.findByLoanId(loanId);
    }
}
