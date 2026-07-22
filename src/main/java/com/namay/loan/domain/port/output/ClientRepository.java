package com.namay.loan.domain.port.output;

import com.namay.loan.domain.model.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> findAll();
    Client findById(Long id);
    Client findByDocument(String document);
    Client save(Client client);
    void toggleStatus(Client client);
}
