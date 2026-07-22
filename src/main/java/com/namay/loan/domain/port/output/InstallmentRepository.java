package com.namay.loan.domain.port.output;

import com.namay.loan.domain.model.Installment;

import java.util.List;

public interface InstallmentRepository {
    List<Installment> findByLoanId(Long loanId);
}
