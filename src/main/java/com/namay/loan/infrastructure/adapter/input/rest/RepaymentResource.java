package com.namay.loan.infrastructure.adapter.input.rest;

import com.namay.loan.domain.port.input.RepaymentUseCase;
import com.namay.loan.infrastructure.adapter.input.rest.dto.RepaymentRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RepaymentResource {

    private final RepaymentUseCase repaymentUseCase;

    public RepaymentResource(RepaymentUseCase repaymentUseCase) {
        this.repaymentUseCase = repaymentUseCase;
    }

    @POST
    public Response payQuota(RepaymentRequest request) {
        this.repaymentUseCase.execute(
                request.loanId(),
                request.installmentId(),
                request.amountPaid(),
                request.processType(),
                request.strategyType());
        return Response.status(Response.Status.CREATED).build();
    }
}
