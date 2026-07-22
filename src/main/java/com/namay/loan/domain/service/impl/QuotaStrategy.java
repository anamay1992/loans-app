package com.namay.loan.domain.service.impl;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.Loan;
import com.namay.loan.domain.service.System;
import com.namay.loan.domain.service.SystemFactory;
import com.namay.loan.domain.service.Strategy;

import java.math.BigDecimal;
import java.util.List;

public class QuotaStrategy implements Strategy {

    private final SystemFactory calculatorFactory;

    public QuotaStrategy(SystemFactory calculatorFactory) {
        this.calculatorFactory = calculatorFactory;
    }

    @Override
    public List<Installment> reduce(Loan loan, Installment currentInstallment, BigDecimal newBalance) {
        int remainingMonths = loan.getOriginalMonths() - currentInstallment.getSequence();
        System calculator = calculatorFactory.get(loan.getSystemType());
        List<Installment> newInstallments = calculator.calculate(
                currentInstallment.getDueDate(),
                newBalance,
                loan.getRate(),
                remainingMonths
        );
        int nextSeq = currentInstallment.getSequence() + 1;
        for (Installment installment : newInstallments) {
            installment.setSequence(nextSeq++);
        }
        return newInstallments;
    }
}
