package com.namay.loan.domain.service.impl;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.constant.InstallmentStatus;
import com.namay.loan.domain.model.Loan;
import com.namay.loan.domain.service.Strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TermStrategy implements Strategy {

    @Override
    public List<Installment> reduce(Loan loan, Installment currentInstallment, BigDecimal newBalance) {
        List<Installment> newInstallments = new ArrayList<>();
        BigDecimal originalQuota = currentInstallment.getAmount();
        BigDecimal monthlyRate = loan.getRate().divide(new BigDecimal("100"), 10,  RoundingMode.HALF_UP);
        int seq = currentInstallment.getSequence() + 1;
        LocalDate nextDate = currentInstallment.getDueDate().plusMonths(1);
        while (newBalance.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal interest = newBalance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal payment;
            BigDecimal principal;
            if (newBalance.add(interest).compareTo(originalQuota) <= 0) {
                payment = newBalance.add(interest);
                principal = newBalance;
                newBalance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            } else {
                payment = originalQuota;
                principal = payment.subtract(interest);
                newBalance = newBalance.subtract(principal);
            }
            newInstallments.add(new Installment(null, seq, payment, principal, interest, newBalance, nextDate, InstallmentStatus.PENDING));
            seq++;
            nextDate = nextDate.plusMonths(1);
        }
        return newInstallments;
    }
}
