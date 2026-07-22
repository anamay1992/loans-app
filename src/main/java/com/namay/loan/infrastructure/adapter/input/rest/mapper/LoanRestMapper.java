package com.namay.loan.infrastructure.adapter.input.rest.mapper;

import com.namay.loan.domain.model.Client;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ClientCreateRequest;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ClientStatusRequest;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ClientUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRestMapper {
    Client toClientDomainFromCreate(ClientCreateRequest request);
    Client toClientDomainFromUpdate(ClientUpdateRequest request);
    Client toClientDomainFromStatus(ClientStatusRequest request);
}
