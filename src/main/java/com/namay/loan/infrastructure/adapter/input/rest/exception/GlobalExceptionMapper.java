package com.namay.loan.infrastructure.adapter.input.rest.exception;

import com.namay.loan.domain.exception.BusinessRuleException;
import com.namay.loan.domain.exception.ResourceNotFoundException;
import com.namay.loan.infrastructure.adapter.input.rest.dto.ErrorResponse;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof BusinessRuleException) {
            return buildResponse(Response.Status.BAD_REQUEST, "BAD_REQUEST", exception.getMessage());
        }
        if (exception instanceof ResourceNotFoundException) {
            return buildResponse(Response.Status.NOT_FOUND, "NOT_FOUND", exception.getMessage());
        }
        if (exception instanceof PersistenceException) {
            System.out.println("Database error: " + exception);
            return  buildResponse(Response.Status.INTERNAL_SERVER_ERROR, "DATABASE_ERROR", "Internal Server Error");
        }
        System.out.println("Unexpected exception:" + exception);
        return buildResponse(Response.Status.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "Internal Server Error");
    }

    private Response buildResponse(Response.Status status, String code, String message) {
        ErrorResponse errorResponse = new ErrorResponse(code, message);
        return Response.status(status)
                .entity(errorResponse)
                .build();
    }
}
