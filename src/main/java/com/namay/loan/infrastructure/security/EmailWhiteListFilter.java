package com.namay.loan.infrastructure.security;

import io.quarkus.security.Authenticated;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.util.List;

@Priority(Priorities.AUTHENTICATION + 10)
@Authenticated
public class EmailWhiteListFilter {

    @Inject
    JsonWebToken jwt;

    @ConfigProperty(name = "app.whitelist.emails")
    List<String> allowedEmails;

    @ServerRequestFilter
    public Response filter() {
        if (jwt != null) {
            String email = jwt.getClaim("email");
            System.out.println(email);
            if (email == null || !allowedEmails.contains(email)) {
                return Response.status(Response.Status.FORBIDDEN)
                        .entity("{\"error\": \"Acceso denegado: Usuario no autorizado para este sistema.\"}")
                        .build();
            }
        }
        return null;
    }
}
