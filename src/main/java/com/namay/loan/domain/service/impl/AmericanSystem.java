package com.namay.loan.domain.service.impl;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.constant.InstallmentStatus;
import com.namay.loan.domain.service.System;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AmericanSystem implements System {

    @Override
    public List<Installment> calculate(LocalDate startDate, BigDecimal capital, BigDecimal rate, int months) {
        List<Installment> installments = new ArrayList<>();
        BigDecimal monthlyRate = rate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
        BigDecimal interestOnly = capital.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
        for (int m = 1; m <= months; m++) {
            BigDecimal principal;
            BigDecimal payment;
            BigDecimal currentBalance;
            if (m == months) {
                principal = capital;
                payment = interestOnly.add(principal);
                currentBalance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            } else {
                principal = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
                payment = interestOnly;
                currentBalance = capital;
            }
            installments.add(new Installment(
                    null,
                    m,
                    payment,
                    principal,
                    interestOnly,
                    currentBalance,
                    startDate.plusMonths(m),
                    InstallmentStatus.PENDING
            ));
        }
        return installments;
    }
}
