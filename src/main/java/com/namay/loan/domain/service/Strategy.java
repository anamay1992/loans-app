package com.namay.loan.domain.service;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.Loan;

import java.math.BigDecimal;
import java.util.List;

public interface Strategy {
    List<Installment> reduce(Loan loan, Installment currentInstallment, BigDecimal newBalance);
}
