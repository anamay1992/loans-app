package com.namay.loan.infrastructure.adapter.output.db;

import com.namay.loan.domain.exception.ResourceNotFoundException;
import com.namay.loan.domain.model.Client;
import com.namay.loan.domain.port.output.ClientRepository;
import com.namay.loan.infrastructure.adapter.output.db.entity.ClientEntity;
import com.namay.loan.infrastructure.adapter.output.db.mapper.LoanDbMapper;
import com.namay.loan.infrastructure.adapter.output.db.repository.PanacheClientRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClientRepositoryAdapter implements ClientRepository {

    private final PanacheClientRepository clientRepository;
    private final LoanDbMapper loanDbMapper;


    public ClientRepositoryAdapter(PanacheClientRepository clientRepository,
                                   LoanDbMapper loanDbMapper) {
        this.clientRepository = clientRepository;
        this.loanDbMapper = loanDbMapper;
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepository.findAll().stream()
                .map(this.loanDbMapper::toClientDomain)
                .toList();
    }

    @Override
    public Client findById(Long id) {
        ClientEntity entity = this.clientRepository.findById(id);
        if (entity == null) {
            throw new ResourceNotFoundException("No se encontró el cliente con el ID: " + id);
        }
        return this.loanDbMapper.toClientDomain(entity);
    }

    @Override
    public Client findByDocument(String document) {
        ClientEntity entity = this.clientRepository.findByDocument(document);
        if (entity == null) {
            throw new ResourceNotFoundException("No se encontró el cliente con el documento: " + document);
        }
        return this.loanDbMapper.toClientDomain(entity);
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = this.loanDbMapper.toClientEntity(client);
        if (entity.getId() == null) {
            this.clientRepository.persist(entity);
        } else {
            entity = this.clientRepository.getEntityManager().merge(entity);
        }
        return this.loanDbMapper.toClientDomain(entity);
    }

    @Override
    public void toggleStatus(Client client) {
        ClientEntity entity = this.clientRepository.findByDocument(client.getDocument());
        if (entity == null) {
            throw new ResourceNotFoundException("No se encontro el cliente con el documento: " + client.getDocument());
        }
        entity.setStatus(client.getStatus());
        this.clientRepository.getEntityManager().merge(entity);
    }
}
