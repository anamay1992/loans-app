package com.namay.loan.application.processor.impl;

import com.namay.loan.domain.exception.BusinessRuleException;
import com.namay.loan.domain.model.*;
import com.namay.loan.domain.model.constant.InstallmentStatus;
import com.namay.loan.domain.model.constant.LoanStatus;
import com.namay.loan.domain.model.constant.RepaymentStrategy;
import com.namay.loan.domain.port.output.LoanRepository;
import com.namay.loan.domain.port.output.RepaymentRepository;
import com.namay.loan.application.processor.Process;
import com.namay.loan.domain.service.Strategy;
import com.namay.loan.domain.service.StrategyFactory;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RegularProcess implements Process {

    private final RepaymentRepository repaymentRepositoryPort;
    private final LoanRepository loanRepositoryPort;
    private final StrategyFactory strategyFactory;

    public RegularProcess(RepaymentRepository repaymentRepositoryPort,
                          LoanRepository loanRepositoryPort,
                          StrategyFactory strategyFactory) {
        this.repaymentRepositoryPort = repaymentRepositoryPort;
        this.loanRepositoryPort = loanRepositoryPort;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public void process(Loan loan,
                        Installment installment,
                        BigDecimal amountPaid,
                        RepaymentStrategy strategyType) {
        BigDecimal extraAmount = getExtraAmount(loan, installment, amountPaid);
        BigDecimal newBalance = installment.getBalance().subtract(extraAmount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal maxAllowed = amountPaid.add(newBalance);
            throw new BusinessRuleException("El monto pagado excede la deuda total. El pago máximo permitido para liquidar el préstamo es de: " + maxAllowed);
        }
        boolean hasExtraPayment = extraAmount.compareTo(BigDecimal.ZERO) > 0;
        boolean isSettled = newBalance.compareTo(BigDecimal.ZERO) == 0;
        RepaymentStrategy appliedStrategy = hasExtraPayment ? strategyType : null;
        List<Installment> newFutureInstallments = new ArrayList<>();
        if (!isSettled && hasExtraPayment) {
            Strategy strategy = strategyFactory.get(strategyType);
            newFutureInstallments = strategy.reduce(loan, installment, newBalance);
        }
        repaymentRepositoryPort.save(new Repayment(
                null,
                loan.getId(),
                installment.getId(),
                amountPaid,
                extraAmount,
                appliedStrategy,
                LocalDateTime.now()));
        if (loan.getInstallments() != null) {
            for (Installment inst : loan.getInstallments()) {
                if (inst.getId().equals(installment.getId())) {
                    inst.setStatus(InstallmentStatus.PAID);
                    if (hasExtraPayment) {
                        inst.setPrincipal(inst.getPrincipal().add(extraAmount));
                        inst.setAmount(inst.getAmount().add(extraAmount));
                        inst.setBalance(newBalance);
                    }
                    break;
                }
            }
        }
        if (isSettled) {
            loan.setStatus(LoanStatus.SETTLED);
            BigDecimal totalProfit = loan.getInstallments().stream()
                    .filter(inst -> inst.getStatus() == InstallmentStatus.PAID)
                    .map(Installment::getInterest)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            loan.setTotalEarned(totalProfit);
            loan.getInstallments().removeIf(inst -> inst.getStatus() == InstallmentStatus.PENDING);
        } else if (hasExtraPayment) {
            loan.getInstallments().removeIf(inst -> inst.getStatus() == InstallmentStatus.PENDING);
            loan.getInstallments().addAll(newFutureInstallments);
            if (!newFutureInstallments.isEmpty()) {
                loan.setEndDate(newFutureInstallments.getLast().getDueDate());
            }
        }
        loanRepositoryPort.save(loan);
    }

    private static BigDecimal getExtraAmount(Loan loan, Installment installment, BigDecimal amountPaid) {
        if (loan.getStatus() == LoanStatus.SETTLED) {
            throw new BusinessRuleException("No se puede procesar un pago en un préstamo que ya está liquidado.");
        }
        BigDecimal normalQuota = installment.getAmount();
        if (amountPaid.compareTo(normalQuota) < 0) {
            throw new BusinessRuleException("El monto pagado (" + amountPaid + ") no puede ser menor a la cuota regular (" + normalQuota + ")");
        }
        return amountPaid.subtract(normalQuota);
    }
}
