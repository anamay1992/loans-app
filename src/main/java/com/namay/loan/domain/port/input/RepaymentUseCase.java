package com.namay.loan.domain.port.input;

import com.namay.loan.domain.model.constant.PaymentProcess;
import com.namay.loan.domain.model.constant.RepaymentStrategy;

import java.math.BigDecimal;

public interface RepaymentUseCase {
    void execute(Long loanId,
                 Long installmentId,
                 BigDecimal amountPaid,
                 PaymentProcess processType,
                 RepaymentStrategy strategyType);
}
