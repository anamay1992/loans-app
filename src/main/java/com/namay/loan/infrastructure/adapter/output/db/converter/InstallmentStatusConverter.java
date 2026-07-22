package com.namay.loan.infrastructure.adapter.output.db.converter;

import com.namay.loan.domain.model.constant.InstallmentStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class InstallmentStatusConverter extends AbstractEnumConverter<InstallmentStatus> {
    public InstallmentStatusConverter() {
        super(InstallmentStatus.class);
    }
}
