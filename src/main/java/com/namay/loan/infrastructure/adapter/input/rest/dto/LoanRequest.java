package com.namay.loan.infrastructure.adapter.input.rest.dto;

import com.namay.loan.domain.model.constant.LoanSystem;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanRequest(
        Long clientId,
        LocalDate startDate,
        BigDecimal capital,
        LoanSystem systemType,
        BigDecimal rate,
        Integer originalMonths
) {}
