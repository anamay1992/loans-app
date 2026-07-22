package com.namay.loan.infrastructure.adapter.output.db.converter;

import com.namay.loan.domain.model.constant.ClientStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ClientStatusConverter extends AbstractEnumConverter<ClientStatus> {
    public ClientStatusConverter() {
        super(ClientStatus.class);
    }
}