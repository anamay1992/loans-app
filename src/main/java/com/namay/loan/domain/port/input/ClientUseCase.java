package com.namay.loan.domain.port.input;

import com.namay.loan.domain.model.Client;

import java.util.List;

public interface ClientUseCase {
    List<Client> getAllClients();
    Client getClientByDocument(String document);
    Client createClient(Client client);
    Client updateClient(Client client);
    void toggleClientStatus(Client client);
}
