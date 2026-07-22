package com.namay.loan.domain.port.input;

import com.namay.loan.domain.model.Loan;
import com.namay.loan.domain.model.constant.LoanSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LoanUseCase {
    List<Loan> getAllLoans();
    List<Loan> getLoansByClientId(Long clientId);
    Loan execute(Long clientId,
                 LocalDate startDate,
                 BigDecimal capital,
                 LoanSystem systemType,
                 BigDecimal rate,
                 Integer originalMonths);
}
