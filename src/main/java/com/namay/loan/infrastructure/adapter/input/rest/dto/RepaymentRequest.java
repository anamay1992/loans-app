package com.namay.loan.infrastructure.adapter.input.rest.dto;

import com.namay.loan.domain.model.constant.PaymentProcess;
import com.namay.loan.domain.model.constant.RepaymentStrategy;

import java.math.BigDecimal;

public record RepaymentRequest(
   Long loanId,
   Long installmentId,
   BigDecimal amountPaid,
   PaymentProcess processType,
   RepaymentStrategy strategyType
) {}
