package com.namay.loan.infrastructure.config;

import com.namay.loan.domain.service.StrategyFactory;
import com.namay.loan.domain.service.SystemFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class DomainConfig {

    @Produces
    @ApplicationScoped
    public SystemFactory createSystemFactory() {
        return new SystemFactory();
    }

    @Produces
    @ApplicationScoped
    public StrategyFactory createStrategyFactory(SystemFactory systemFactory) {
        return new StrategyFactory(systemFactory);
    }
}
