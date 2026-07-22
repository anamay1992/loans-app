package com.namay.loan.infrastructure.security;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.util.List;

@Priority(Priorities.AUTHENTICATION + 10)
public class EmailWhiteListFilter {

    @Inject
    JsonWebToken jwt;

    private static final List<String> ALLOWED_EMAILS = List.of(
            "angel.n.cabanillas@gmail.com",
            "leslycamacho2018@gmail.com"
    );

    @ServerRequestFilter
    public Response filter() {
        if (jwt != null) {
            String email = jwt.getClaim("email");
            System.out.println(email);
            if (email == null || !ALLOWED_EMAILS.contains(email)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"Acceso denegado: Usuario no autorizado para este sistema.\"}")
                        .build();
            }
        }
        return null;
    }
}
