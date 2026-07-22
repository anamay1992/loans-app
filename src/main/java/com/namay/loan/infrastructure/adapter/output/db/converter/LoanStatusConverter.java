package com.namay.loan.infrastructure.adapter.output.db.converter;

import com.namay.loan.domain.model.constant.LoanStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LoanStatusConverter extends AbstractEnumConverter<LoanStatus> {
    public LoanStatusConverter() {
        super(LoanStatus.class);
    }
}
