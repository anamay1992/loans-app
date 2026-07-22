package com.namay.loan.domain.service;

import com.namay.loan.domain.model.Installment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface System {
    List<Installment> calculate(LocalDate startDate, BigDecimal capital, BigDecimal rate, int months);
}
