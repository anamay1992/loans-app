package com.namay.loan.application.processor.impl;

import com.namay.loan.domain.exception.BusinessRuleException;
import com.namay.loan.domain.model.*;
import com.namay.loan.domain.model.constant.InstallmentStatus;
import com.namay.loan.domain.model.constant.RepaymentStrategy;
import com.namay.loan.domain.port.output.LoanRepository;
import com.namay.loan.domain.port.output.RepaymentRepository;
import com.namay.loan.application.processor.Process;
import com.namay.loan.domain.service.SystemFactory;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class InterestOnlyProcess implements Process {

    private final LoanRepository loanRepositoryPort;
    private final RepaymentRepository repaymentRepositoryPort;
    private final SystemFactory systemFactory;

    public InterestOnlyProcess(LoanRepository loanRepositoryPort,
                               RepaymentRepository repaymentRepositoryPort,
                               SystemFactory systemFactory) {
        this.loanRepositoryPort = loanRepositoryPort;
        this.repaymentRepositoryPort = repaymentRepositoryPort;
        this.systemFactory = systemFactory;
    }

    @Override
    public void process(Loan loan,
                        Installment installment,
                        BigDecimal amountPaid,
                        RepaymentStrategy strategyType) {
        BigDecimal interestDue = getInterestDue(installment, amountPaid);
        this.repaymentRepositoryPort.save(new Repayment(
                null,
                loan.getId(),
                installment.getId(),
                amountPaid,
                BigDecimal.ZERO,
                null,
                LocalDateTime.now()));
        BigDecimal previousBalance = installment.getBalance().add(installment.getPrincipal());
        if (loan.getInstallments() != null) {
            for (Installment inst : loan.getInstallments()) {
                if (inst.getId().equals(installment.getId())) {
                    inst.setAmount(interestDue);
                    inst.setPrincipal(BigDecimal.ZERO);
                    inst.setBalance(previousBalance);
                    inst.setStatus(InstallmentStatus.PAID);
                    break;
                }
            }
        }
        int remainingMonths = loan.getOriginalMonths() - installment.getSequence() + 1;
        var calculator = this.systemFactory.get(loan.getSystemType());
        List<Installment> newFutureInstallments = calculator.calculate(
                installment.getDueDate(), previousBalance, loan.getRate(), remainingMonths);
        int nextSeq = installment.getSequence() + 1;
        for (Installment inst : newFutureInstallments) {
            inst.setSequence(nextSeq++);
            inst.setLoanId(loan.getId());
        }
        if (loan.getInstallments() != null) {
            loan.getInstallments().removeIf(inst -> inst.getStatus() == InstallmentStatus.PENDING);
            loan.getInstallments().addAll(newFutureInstallments);
        }
        if (!newFutureInstallments.isEmpty()) {
            loan.setEndDate(newFutureInstallments.getLast().getDueDate());
        }
        this.loanRepositoryPort.save(loan);
    }

    private static BigDecimal getInterestDue(Installment installment, BigDecimal amountPaid) {
        BigDecimal interestDue = installment.getInterest();
        if (amountPaid.compareTo(interestDue) < 0) {
            throw new BusinessRuleException("El monto pagado (" + amountPaid + ") no puede ser menor al interés de la cuota (" + interestDue + ").");
        }
        if (amountPaid.compareTo(interestDue) > 0) {
            throw new BusinessRuleException("En un pago de 'Solo Interés', el monto debe ser exacto (" + interestDue + "). Para abonar excedentes a capital, utilice un pago regular.");
        }
        return interestDue;
    }
}
