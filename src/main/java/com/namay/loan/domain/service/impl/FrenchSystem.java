package com.namay.loan.domain.service.impl;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.constant.InstallmentStatus;
import com.namay.loan.domain.service.System;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrenchSystem implements System {

    @Override
    public List<Installment> calculate(LocalDate startDate, BigDecimal capital, BigDecimal rate, int months) {
        List<Installment> installments = new ArrayList<>();
        BigDecimal monthlyRate = rate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
        double i = monthlyRate.doubleValue();
        double fixedPayment = capital.doubleValue() * (i * Math.pow(1 + i, months)) / (Math.pow(1 + i, months) - 1);
        BigDecimal quota = BigDecimal.valueOf(fixedPayment).setScale(2, RoundingMode.HALF_UP);
        BigDecimal remainingBalance = capital;
        for (int m = 1; m <= months; m++) {
            BigDecimal interest = remainingBalance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal principal = quota.subtract(interest);
            if (m == months) {
                principal = remainingBalance;
                quota = principal.add(interest);
            }
            remainingBalance = remainingBalance.subtract(principal);
            installments.add(new Installment(
                    null,
                    m,
                    quota,
                    principal,
                    interest,
                    remainingBalance,
                    startDate.plusMonths(m),
                    InstallmentStatus.PENDING));
        }
        return installments;
    }
}
