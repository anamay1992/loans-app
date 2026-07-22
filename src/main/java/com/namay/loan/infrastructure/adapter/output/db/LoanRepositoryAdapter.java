package com.namay.loan.infrastructure.adapter.output.db;

import com.namay.loan.domain.exception.ResourceNotFoundException;
import com.namay.loan.domain.model.Loan;
import com.namay.loan.domain.port.output.LoanRepository;
import com.namay.loan.infrastructure.adapter.output.db.entity.LoanEntity;
import com.namay.loan.infrastructure.adapter.output.db.mapper.LoanDbMapper;
import com.namay.loan.infrastructure.adapter.output.db.repository.PanacheLoanRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class LoanRepositoryAdapter implements LoanRepository {

    private final PanacheLoanRepository loanRepository;
    private final LoanDbMapper loanDbMapper;

    @Inject
    public LoanRepositoryAdapter(PanacheLoanRepository loanRepository,
                                 LoanDbMapper loanDbMapper) {
        this.loanRepository = loanRepository;
        this.loanDbMapper = loanDbMapper;
    }

    @Override
    public List<Loan> findAll() {
        return this.loanRepository.findAll().stream()
                .map(this.loanDbMapper::toLoanDomain)
                .toList();
    }

    @Override
    public List<Loan> findAllByClientId(Long clientId) {
        return this.loanRepository.findByClientId(clientId).stream()
                .map(this.loanDbMapper::toLoanDomain)
                .toList();
    }

    @Override
    public Loan findById(Long id) {
        LoanEntity entity = this.loanRepository.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("No se encontró el préstamo con el ID: " + id);
        }
        return this.loanDbMapper.toLoanDomainFull(entity);
    }

    @Override
    public Loan save(Loan loan) {
        LoanEntity entity = this.loanDbMapper.toLoanEntity(loan);
        System.out.println(entity.getId());
        if (entity.getId() == null) {
            this.loanRepository.persist(entity);
        } else {
            entity = this.loanRepository.getEntityManager().merge(entity);
        }
        return this.loanDbMapper.toLoanDomainFull(entity);
    }
}
