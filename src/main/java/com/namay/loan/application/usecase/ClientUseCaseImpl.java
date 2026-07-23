package com.namay.loan.application.usecase;

import com.namay.loan.domain.model.Client;
import com.namay.loan.domain.model.constant.ClientStatus;
import com.namay.loan.domain.port.input.ClientUseCase;
import com.namay.loan.domain.port.output.ClientRepository;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheResult;
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
    @CacheResult(cacheName = "clients-cache")
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
    @CacheInvalidateAll(cacheName = "clients-cache")
    public Client createClient(Client client) {
        client.setStatus(ClientStatus.ACTIVE);
        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    @CacheInvalidateAll(cacheName = "clients-cache")
    public Client updateClient(Client client) {
        Client existingClient = this.clientRepository.findById(client.getId());
        existingClient.setFullName(client.getFullName());
        existingClient.setPhoneNumber(client.getPhoneNumber());
        existingClient.setEmail(client.getEmail());
        return this.clientRepository.save(existingClient);
    }

    @Override
    @Transactional
    @CacheInvalidateAll(cacheName = "clients-cache")
    public void toggleClientStatus(Client client) {
        Client existingClient = this.clientRepository.findById(client.getId());
        existingClient.setStatus(client.getStatus());
        this.clientRepository.toggleStatus(existingClient);
    }
}
