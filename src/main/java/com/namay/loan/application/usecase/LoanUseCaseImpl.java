package com.namay.loan.application.usecase;

import com.namay.loan.domain.exception.BusinessRuleException;
import com.namay.loan.domain.model.*;
import com.namay.loan.domain.model.constant.LoanStatus;
import com.namay.loan.domain.model.constant.LoanSystem;
import com.namay.loan.domain.port.input.LoanUseCase;
import com.namay.loan.domain.port.output.ClientRepository;
import com.namay.loan.domain.port.output.LoanRepository;
import com.namay.loan.domain.service.System;
import com.namay.loan.domain.service.SystemFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class LoanUseCaseImpl implements LoanUseCase {

    private final ClientRepository clientRepository;
    private final LoanRepository loanRepository;
    private final SystemFactory systemFactory;

    public LoanUseCaseImpl(ClientRepository clientRepository,
                           LoanRepository loanRepository,
                           SystemFactory systemFactory) {
        this.clientRepository = clientRepository;
        this.loanRepository = loanRepository;
        this.systemFactory = systemFactory;
    }

    @Override
    @Transactional
    public List<Loan> getAllLoans() {
        return this.loanRepository.findAll();
    }

    @Override
    @Transactional
    public List<Loan> getLoansByClientId(Long clientId) {
        return loanRepository.findAllByClientId(clientId);
    }

    @Override
    @Transactional
    public Loan execute(Long clientId,
                        LocalDate startDate,
                        BigDecimal capital,
                        LoanSystem systemType,
                        BigDecimal rate,
                        Integer originalMonths) {
        LocalDate baseDate = (startDate != null) ? startDate : LocalDate.now();
        Client client = clientRepository.findById(clientId);
        System system = this.systemFactory.get(systemType);
        List<Installment> installments = system.calculate(baseDate, capital, rate, originalMonths);
        if (installments == null || installments.isEmpty()) {
            throw new BusinessRuleException("Error crítico: El sistema no pudo generar el cronograma de pagos para el préstamo.");
        }
        Loan loan = new Loan(
                null,
                client.getId(),
                baseDate,
                installments.getLast().getDueDate(),
                capital,
                systemType,
                rate,
                originalMonths,
                installments,
                BigDecimal.ZERO,
                LoanStatus.ACTIVE);
        return this.loanRepository.save(loan);
    }
}
