package com.namay.loan.infrastructure.adapter.input.rest;

import com.namay.loan.domain.model.Client;
import com.namay.loan.domain.port.input.ClientUseCase;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ClientCreateRequest;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ClientStatusRequest;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ClientUpdateRequest;
import com.namay.loan.infrastructure.adapter.input.rest.mapper.LoanRestMapper;
import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private final ClientUseCase clientUseCase;
    private final LoanRestMapper loanRestMapper;

    public ClientResource(ClientUseCase clientUseCase,
                          LoanRestMapper loanRestMapper) {
        this.clientUseCase = clientUseCase;
        this.loanRestMapper = loanRestMapper;
    }

    @GET
    public Response getAllClients() {
        return Response.status(Response.Status.OK)
                .entity(this.clientUseCase.getAllClients())
                .build();
    }

    @GET
    @Path("/{document}")
    public Response getClientByDocument(@PathParam("document") String document) {
        return Response.status(Response.Status.OK)
                .entity(this.clientUseCase.getClientByDocument(document))
                .build();
    }

    @POST
    public Response createClient(@Valid ClientCreateRequest request) {
        Client client = loanRestMapper.toClientDomainFromCreate(request);
        return Response.status(Response.Status.CREATED)
                .entity(this.clientUseCase.createClient(client))
                .build();
    }

    @PUT
    public Response updateClient(@Valid ClientUpdateRequest request) {
        Client client = loanRestMapper.toClientDomainFromUpdate(request);
        return Response.status(Response.Status.CREATED)
                .entity(this.clientUseCase.updateClient(client))
                .build();
    }

    @PATCH
    public Response toggleClient(@Valid ClientStatusRequest request) {
        Client client = loanRestMapper.toClientDomainFromStatus(request);
        this.clientUseCase.toggleClientStatus(client);
        return Response.status(Response.Status.CREATED)
                .build();
    }
}
