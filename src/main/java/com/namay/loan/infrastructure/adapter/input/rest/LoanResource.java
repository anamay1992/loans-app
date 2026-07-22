package com.namay.loan.infrastructure.adapter.input.rest;

import com.namay.loan.domain.port.input.LoanUseCase;
import com.namay.loan.infrastructure.adapter.input.rest.dto.LoanRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/loans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanResource {

    private final LoanUseCase loanUseCase;

    public LoanResource(LoanUseCase loanUseCase) {
        this.loanUseCase = loanUseCase;
    }

    @GET
    public Response getAllLoans() {
        return Response.status(Response.Status.OK)
                .entity(this.loanUseCase.getAllLoans())
                .build();
    }

    @GET
    @Path("/{clientId}")
    public Response getLoansByDocument(@PathParam("clientId") Long clientId) {
        return Response.status(Response.Status.OK)
                .entity(this.loanUseCase.getLoansByClientId(clientId))
                .build();
    }

    @POST
    public Response createLoan(LoanRequest request) {
        return Response.status(Response.Status.CREATED)
                .entity(this.loanUseCase.execute(
                        request.clientId(),
                        request.startDate(),
                        request.capital(),
                        request.systemType(),
                        request.rate(),
                        request.originalMonths()))
                .build();
    }
}
