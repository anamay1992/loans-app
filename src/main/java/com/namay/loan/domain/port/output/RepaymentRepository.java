package com.namay.loan.domain.port.output;

import com.namay.loan.domain.model.Repayment;

public interface RepaymentRepository {
    Repayment save(Repayment repayment);
}
