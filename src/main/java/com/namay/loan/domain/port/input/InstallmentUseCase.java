package com.namay.loan.domain.port.input;

import com.namay.loan.domain.model.Installment;

import java.util.List;

public interface InstallmentUseCase {
    List<Installment> getInstallmentsByLoanId(Long loanId);
}
