package com.namay.loan.infrastructure.adapter.output.db.mapper;

import com.namay.loan.domain.model.Client;
import com.namay.loan.domain.model.Installment;
import com.namay.loan.domain.model.Loan;
import com.namay.loan.domain.model.Repayment;
import com.namay.loan.infrastructure.adapter.output.db.entity.ClientEntity;
import com.namay.loan.infrastructure.adapter.output.db.entity.InstallmentEntity;
import com.namay.loan.infrastructure.adapter.output.db.entity.LoanEntity;
import com.namay.loan.infrastructure.adapter.output.db.entity.RepaymentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanDbMapper {

    Client toClientDomain(ClientEntity entity);
    ClientEntity toClientEntity(Client client);

    @Named("fullMapper")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.fullName", target = "clientName")
    Loan toLoanDomainFull(LoanEntity entity);

    @Named("summaryMapper")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.fullName", target = "clientName")
    @Mapping(target = "installments", ignore = true)
    Loan toLoanDomain(LoanEntity entity);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "clientName", target = "client.fullName")
    LoanEntity toLoanEntity(Loan loan);

    Installment toInstallmentDomain(InstallmentEntity entity);
    InstallmentEntity toInstallmentEntity(Installment installment);

    Repayment toRepaymentDomain(RepaymentEntity entity);
    RepaymentEntity toRepaymentEntity(Repayment repayment);
}
