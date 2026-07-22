package com.namay.loan.application.processor;

import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.Loan;
import com.namay.loan.domain.model.constant.RepaymentStrategy;

import java.math.BigDecimal;

public interface Process {
    void process(Loan loan,
                 Installment installment,
                 BigDecimal amountPaid,
                 RepaymentStrategy strategyType);
}
