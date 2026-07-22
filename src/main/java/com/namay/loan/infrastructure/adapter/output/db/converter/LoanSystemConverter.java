package com.namay.loan.infrastructure.adapter.output.db.converter;

import com.namay.loan.domain.model.constant.LoanSystem;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LoanSystemConverter extends AbstractEnumConverter<LoanSystem> {
    public LoanSystemConverter() {
        super(LoanSystem.class);
    }
}
