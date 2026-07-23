package com.namay.loan.application.usecase;

import com.namay.loan.application.processor.Process;
import com.namay.loan.application.processor.ProcessFactory;
import com.namay.loan.domain.exception.BusinessRuleException;
import com.namay.loan.domain.model.*;
import com.namay.loan.domain.model.constant.InstallmentStatus;
import com.namay.loan.domain.model.constant.PaymentProcess;
import com.namay.loan.domain.model.constant.RepaymentStrategy;
import com.namay.loan.domain.port.input.RepaymentUseCase;
import com.namay.loan.domain.port.output.LoanRepository;
import io.quarkus.cache.CacheInvalidateAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

@ApplicationScoped
public class RepaymentUseCaseImpl implements RepaymentUseCase {

    private final LoanRepository loanRepositoryPort;
    private final ProcessFactory processFactory;

    public RepaymentUseCaseImpl(LoanRepository loanRepositoryPort,
                                ProcessFactory processFactory) {
        this.loanRepositoryPort = loanRepositoryPort;
        this.processFactory = processFactory;
    }

    @Override
    @Transactional
    @CacheInvalidateAll(cacheName = "loans-cache")
    public void execute(Long loanId,
                        Long installmentId,
                        BigDecimal amountPaid,
                        PaymentProcess processType,
                        RepaymentStrategy strategyType) {
        if (amountPaid == null || amountPaid.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRuleException("El monto a pagar debe ser mayor a cero.");
        }
        Loan loan = this.loanRepositoryPort.findById(loanId);

        Installment targetInstallment = loan.getInstallments().stream()
                .filter(inst -> inst.getId().equals(installmentId))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("La cuota con ID " + installmentId + " no pertenece a este préstamo."));
        if (targetInstallment.getStatus() == InstallmentStatus.PAID) {
            throw new BusinessRuleException("La cuota seleccionada ya se encuentra pagada.");
        }
        Process processor = this.processFactory.get(processType);
        processor.process(loan, targetInstallment, amountPaid, strategyType);
    }
}
