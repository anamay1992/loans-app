package com.namay.loan.infrastructure.adapter.input.rest;

import com.namay.loan.domain.port.input.InstallmentUseCase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/installments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstallmentResource {

    private final InstallmentUseCase installmentUseCase;

    public InstallmentResource(InstallmentUseCase installmentUseCase) {
        this.installmentUseCase = installmentUseCase;
    }

    @GET
    @Path("/{loanId}")
    public Response getInstallmentsByLoanId(@PathParam("loanId") Long loanId) {
        return Response.status(Response.Status.OK)
                .entity(this.installmentUseCase.getInstallmentsByLoanId(loanId))
                .build();
    }
}
