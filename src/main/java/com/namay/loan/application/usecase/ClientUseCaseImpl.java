package com.namay.loan.application.usecase;

import com.namay.loan.domain.model.Client;
import com.namay.loan.domain.model.constant.ClientStatus;
import com.namay.loan.domain.port.input.ClientUseCase;
import com.namay.loan.domain.port.output.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientRepository clientRepository;

    public ClientUseCaseImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public List<Client> getAllClients() {
        return this.clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client getClientByDocument(String document) {
        return this.clientRepository.findByDocument(document);
    }

    @Override
    @Transactional
    public Client createClient(Client client) {
        client.setStatus(ClientStatus.ACTIVE);
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public void toggleClientStatus(Client client) {
        this.clientRepository.toggleStatus(client);
    }
}
