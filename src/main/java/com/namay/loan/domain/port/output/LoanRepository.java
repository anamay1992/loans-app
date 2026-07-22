package com.namay.loan.domain.port.output;

import com.namay.loan.domain.model.Loan;

import java.util.List;

public interface LoanRepository {
    List<Loan> findAll();
    List<Loan> findAllByClientId(Long clientId);
    Loan findById(Long id);
    Loan save(Loan loan);
}
