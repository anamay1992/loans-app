package com.namay.loan.application.usecase;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.port.input.InstallmentUseCase;
import com.namay.loan.domain.port.output.InstallmentRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InstallmentUseCaseImpl implements InstallmentUseCase {

    private final InstallmentRepository installmentRepository;

    public InstallmentUseCaseImpl(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @Override
    public List<Installment> getInstallmentsByLoanId(Long loanId) {
        return this.installmentRepository.findByLoanId(loanId);
    }
}
