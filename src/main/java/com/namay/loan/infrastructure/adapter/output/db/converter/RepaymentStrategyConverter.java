package com.namay.loan.infrastructure.adapter.output.db.converter;

import com.namay.loan.domain.model.constant.RepaymentStrategy;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RepaymentStrategyConverter extends AbstractEnumConverter<RepaymentStrategy> {
    public RepaymentStrategyConverter() {
        super(RepaymentStrategy.class);
    }
}
